package com.loopcreative.web.admin.service;

import com.loopcreative.web.admin.repository.CreditsAdminRepository;
import com.loopcreative.web.admin.repository.VideoAdminRepository;
import com.loopcreative.web.admin.repository.WorkAdminRepository;
import com.loopcreative.web.admin.validate.WorkAdminServiceVali;
import com.loopcreative.web.dto.WorkDto;
import com.loopcreative.web.entity.Credits;
import com.loopcreative.web.entity.Video;
import com.loopcreative.web.entity.Work;
import com.loopcreative.web.error.RestApiException;
import com.loopcreative.web.error.UserErrorCode;
import com.loopcreative.web.file.service.FileService;
import com.loopcreative.web.form.CreditsForm;
import com.loopcreative.web.form.FileForm;
import com.loopcreative.web.form.VideoForm;
import com.loopcreative.web.form.WorkForm;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class WorkAdminService {

    private final WorkAdminRepository workAdminRepository;
    private final WorkAdminServiceVali workAdminServiceVali;
    private final FileService fileService;
    private final CreditsAdminRepository creditsAdminRepository;
    private final VideoAdminRepository videoAdminRepository;
    private final EntityManager em;

    /**
     * 1. Work 리스트 페이징 처리 및 Dto로 반환
     * @param pageable
     * @return
     */
    public Page<WorkDto> findWorkAll(Pageable pageable){
        Page<Work> works = workAdminRepository.findAll(pageable);
        workAdminServiceVali.hasList(works);
        return works.map(w -> new WorkDto(w));
    }

    /**
     * 1. Work 와 Files는 PatchJoin 처리로 쿼리 최적화 후 반환
     *   (default_batch_fetch_size 최적화 진행,
     *     연관관계 테이블은 get 하는 경우 10개씩 where in절로 진행)
     * 3. 파일이 없는 Work인 경우 재조회해서 반환
     * @param id
     * @return
     */
    public WorkDto findWorkFileById(Long id){
        String str = "thumb_nail";
        Optional<Work> findWork = workAdminRepository.findWorkFileById(id);

        if(!findWork.isEmpty()){
            return findWork.get().changeDto();
        }

        return workAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT)).changeDto();

    }

    /**
     * 1. Work, Credits, Video는 함께 저장된다.
     * 2. Credits, Video 는 문자열 null 데이터가 오는 경우 순서에 밀리지 않고 문자열이 아닌 null로 변환하여 저장
     * @param workForm
     * @param creditsForms
     * @param videoForms
     * @param saveFileForms
     * @param thumbnailFileForm
     * @param removeFileForms
     * @return
     */
    @Transactional
    public WorkDto save(WorkForm workForm, List<CreditsForm> creditsForms, List<VideoForm> videoForms, List<FileForm> saveFileForms, FileForm thumbnailFileForm, List<Long> removeFileForms){
        workForm.setUseYn("Y");
        Work work = workForm.toEntity();

        //workAddVideo(videoForm, work); 배열로 받을 시 진행
        //workAddCredits(creditsForm, work); 배열로 받을 시 진행

        for(int i = 0; i < creditsForms.size(); i++){
                String job = creditsForms.get(i).getJob();
                String name = creditsForms.get(i).getName();
                work.addCredits(new Credits(i,job,name));
        }

        for(int i = 0; i < videoForms.size(); i++){
                String url = videoForms.get(i).getVideoUrl();
                String title = videoForms.get(i).getVideoTitle();
                String content = videoForms.get(i).getVideoContent();
                String type = videoForms.get(i).getVideoType();
                Integer ord = videoForms.get(i).getOrd();
                work.addVideo(new Video(url, title, content, type, ord));
        }

        Work saveWork = workAdminRepository.save(work);


        //사진 파일 정보 및 부모키 업데이트 --***** video Id 업데이트 쿼리에 추가
        alreadyFileWorkAndVideoParentsIdUpdate(saveFileForms, saveWork);

        //썸네일사진 정보 및 부모키 업데이트
        alreadyThumbnailFileParentsIdUpdate(thumbnailFileForm,saveWork);
        //삭제 파일 로직
        fileDelete(removeFileForms);

        WorkDto workDto = saveWork.changeDto();
        return workDto;
    }

    /**
     * 1. Work는 상세페이지에서 첨부파일을 미리 저장 시켜두었다. (속도문제)
     * 2. Work 저장 시 미리 저장 된 첨부파일의 외래키(work_no)를 업데이트
     * 3. Video 저장 시 미리 저장 된 첨부파일의 외래키(work_no)를 업데이트
     * @param fileForms
     * @param saveWork
     */
    private void alreadyFileWorkAndVideoParentsIdUpdate(List<FileForm> fileForms, Work saveWork) {
        if(fileForms != null){

            for (FileForm fileform : fileForms) {
                for(Video video : saveWork.getVideos()){
                    if(fileform.getOrd() == video.getOrd()){
                        fileform.setVideoId(video.getId());
                    }
                }
                fileService.filesWorkIdUpdate(saveWork.getId(),fileform.getId(), fileform.getVideoId()
                        ,fileform.getTmplType(),fileform.getOrd(), fileform.getPicOrd());

            }
        }
    }
    /**
     * 1. Work는 상세페이지에서 첨부파일을 미리 저장 시켜두었다. (속도문제)
     * 2. Work 저장 시 미리 저장 된 첨부파일의 외래키(work_no)를 업데이트
     * @param thumbnailFileForm
     * @param saveWork
     */
    private void alreadyThumbnailFileParentsIdUpdate(FileForm thumbnailFileForm, Work saveWork) {
        if(thumbnailFileForm != null){
                fileService.filesThumbnailWorkIdUpdate(saveWork.getId(),thumbnailFileForm.getId(),thumbnailFileForm.getCd());

        }
    }

    /**
     * 1. 파일 삭제
     * @param removeFileForms
     */
    private void fileDelete(List<Long> removeFileForms) {
        for (Long removeFileId : removeFileForms) {
            fileService.deleteById(removeFileId);
        }
    }
    /**
     * 1. Work 객체에 Credits 객체 set
     * @param creditsForm
     * @param work
     */
    /*
    private void workAddCredits(CreditsForm creditsForm, Work work) {
        if(creditsForm.getCreditsOrd() != null){
            for(int i = 0; i < creditsForm.getCreditsOrd().length; i++){
                Integer ord = creditsForm.getCreditsOrd()[i];
                String job = getValueOrNull(creditsForm.getCreditsJob(),i);
                String name = getValueOrNull(creditsForm.getCreditsName(),i);

                work.addCredits(new Credits(ord,job,name));
            }
        }
    }
*/
    /**
     * 1. Work 객체에 Video 객체 set
     * @param videoForm
     * @param work
     */
    /*
    private void workAddVideo(VideoForm videoForm, Work work) {
        if (videoForm.getVideoOrd() != null) {
            for (int i = 0; i < videoForm.getVideoOrd().length; i++) {
                Integer ord = videoForm.getVideoOrd()[i];
                String url = getValueOrNull(videoForm.getVideoUrl(), i);
                String type = getValueOrNull(videoForm.getVideoType(), i);
                String title = getValueOrNull(videoForm.getVideoTitle(), i);
                String content = getValueOrNull(videoForm.getVideoContent(), i);

                work.addVideo(new Video(url, title, content, type, ord));
            }
        }
    }
*/
    /**
     * 1. 문자열 "null"이거나 값이 없을 경우에 null 반환
     * @param array
     * @param index
     * @return
     */
    private String getValueOrNull(String[] array, int index) {
        return (array != null && index < array.length ) ? ("null".equals(array[index]) ? null : array[index]) : null;
    }

    /**
     * 1. value의 값이 없을 경우 set 하지 않는다.
     * @param value
     * @param setter
     * @param <T>
     */
    private <T> void updateAttributeIfNotNull(String value, Consumer<String> setter) {
        if (value != null && !Objects.equals(value, "null")) {
            setter.accept(value);
        }
    }
    /**
     * 1. Work, Credits, Video 함께 업데이트
     * 2. 조회 후 업데이트 로직 반복되며, null 처리 진행
     * @param workForm
     * @param creditsForms
     * @param videoForms
     * @param saveFileForms
     * @param thumbnailFileForm
     * @param removeFileForms
     * @param removeVideoForms
     * @param removeCreditsForms
     * @return
     */
    @Transactional
    public WorkDto update(WorkForm workForm, List<CreditsForm> creditsForms, List<VideoForm> videoForms, List<FileForm> saveFileForms, FileForm thumbnailFileForm, List<Long> removeFileForms, List<Long> removeVideoForms, List<Long> removeCreditsForms) {
        Work findWork = workAdminRepository.findById(workForm.getWorkId())
                .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));

        //1. work
        updateAttributeIfNotNull(workForm.getWorkTitle(), value -> findWork.setWorkTitle(value));
        updateAttributeIfNotNull(workForm.getWorkType(), value -> findWork.setWorkType(value));
        updateAttributeIfNotNull(workForm.getUseYn(), value -> findWork.setUseYn(value));

        //2. video
        // Videos를 삭제
        List<Video> videosToRemove = new ArrayList<>();
        for (Long id : removeVideoForms) {
            Iterator<Video> iterator = findWork.getVideos().iterator();
            while (iterator.hasNext()) {
                Video video = iterator.next();
                if (id.equals(video.getId())) {
                    videosToRemove.add(video);
                    iterator.remove();
                }
            }
        }
        for (Video videoToRemove : videosToRemove) {
            videoAdminRepository.deleteById(videoToRemove.getId());
        }

        //기존에 있는 아이디면 내용수정
        if(videoForms != null && !videoForms.isEmpty()){
            for (VideoForm videoForm : videoForms) {
                Optional<Video> findVideoOptional = videoAdminRepository.findById(videoForm.getId());

                if(findVideoOptional.isEmpty()){
                //video id는 0으로 잡히지 않는다 추가 된 것으로 확인하면
                    String url = videoForm.getVideoUrl();
                    String title = videoForm.getVideoTitle();
                    String content = videoForm.getVideoContent();
                    String type = videoForm.getVideoType();
                    Integer ord = videoForm.getOrd();
                    findWork.addVideo(new Video(url, title, content, type, ord));
                }else{
                    Video findVideo = findVideoOptional.orElseThrow(() -> new RestApiException(UserErrorCode.EXCEPTION_BASIC));
                    findVideo.setVideoUrl(videoForm.getVideoUrl());
                    findVideo.setVideoTitle(videoForm.getVideoTitle());
                    findVideo.setVideoContent(videoForm.getVideoContent());
                    findVideo.setVideoType(videoForm.getVideoType());
                    findVideo.setOrd(videoForm.getOrd());
                }
            }
        }
        //3. file
        //file del 지우기
        // ***정보 넣을 때에 videoId 찾아서 넣는 로직 추가
        for(FileForm fileForm : saveFileForms){
            for(Video video : findWork.getVideos()){
                if(fileForm.getOrd() == video.getOrd()){
                    fileForm.setVideoId(video.getId());
                }
            }
        }
        //삭제 파일 로직
        fileDelete(removeFileForms);
        //사진 파일 정보 및 부모키 업데이트
        alreadyFileWorkAndVideoParentsIdUpdate(saveFileForms, findWork);
        //썸네일사진 정보 및 부모키 업데이트
        alreadyThumbnailFileParentsIdUpdate(thumbnailFileForm,findWork);


        // 4. credits
        // Credits 삭제
        List<Credits> creditsToRemove = new ArrayList<>();
        for (Long id : removeCreditsForms) {
            Iterator<Credits> iterator = findWork.getCredits().iterator();
            while (iterator.hasNext()) {
                Credits credits = iterator.next();
                if (id.equals(credits.getId())) {
                    creditsToRemove.add(credits);
                    iterator.remove();
                }
            }
        }
        for (Credits creditToRemove : creditsToRemove) {
            creditsAdminRepository.deleteById(creditToRemove.getId());
        }
        //credits 기존 id가 0이면 새로 저장
        //기존에 있는 아이디면 내용수정
        if(creditsForms != null){
            for (int i=0; i < creditsForms.size(); i++) {
                Optional<Credits> findCreditsOptional = creditsAdminRepository.findById(creditsForms.get(i).getId());
                if(findCreditsOptional.isEmpty()){
                    //video id는 0으로 잡히지 않는다 추가 된 것으로 확인하면
                    String job = creditsForms.get(i).getJob();
                    String name = creditsForms.get(i).getName();
                    Credits credits = new Credits(i, job, name);
                    findWork.addCredits(new Credits(i,job,name));
                }else{
                    Credits findCredits = findCreditsOptional.orElseThrow(() -> new RestApiException(UserErrorCode.EXCEPTION_BASIC));
                    findCredits.setJob(creditsForms.get(i).getJob());
                    findCredits.setName(creditsForms.get(i).getName());
                }
            }
        }
        //순서 정렬
        List<Credits> findCredits = findWork.getCredits();
        for(int i=0; i<findCredits.size(); i++){
            int j = i + 1;
            findCredits.get(i).setOrd(j);
        }

        return findWork.changeDto();
    }

}
