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
import com.loopcreative.web.form.VideoForm;
import com.loopcreative.web.form.WorkForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
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

    public Page<WorkDto> findWorkAll(Pageable pageable){
        Page<Work> works = workAdminRepository.findAll(pageable);
        workAdminServiceVali.hasList(works);
        return works.map(w -> new WorkDto(w));
    }

    public WorkDto findWorkFileById(Long id){
        String str = "thumb_nail";
        Optional<Work> findWork = workAdminRepository.findWorkFileById(id);

        if(!findWork.isEmpty()){
            return findWork.get().changeDto();
        }

        return workAdminRepository.findById(id)
                .orElseThrow(() ->new RestApiException(UserErrorCode.NO_RESULT)).changeDto();

    }

    @Transactional
    public WorkDto save(WorkForm workForm, CreditsForm creditsForm, VideoForm videoForm) {
        workForm.setUseYn("Y");
        Work work = workForm.toEntity();

        workAddVideo(videoForm, work);
        workAddCredits(creditsForm, work);

        Work saveWork = workAdminRepository.save(work);
        alreadyFileParentsIdUpdate(workForm, saveWork);

        WorkDto workDto = saveWork.changeDto();
        return workDto;
    }

    @Transactional
    public WorkDto update(WorkForm workForm, CreditsForm creditsForm, VideoForm videoForm) {
        Work findWork = workAdminRepository.findById(workForm.getWorkId())
                .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));

        updateAttributeIfNotNull(workForm.getWorkTitle(), value -> findWork.setWorkTitle(value));
        updateAttributeIfNotNull(workForm.getWorkType(), value -> findWork.setWorkType(value));
        updateAttributeIfNotNull(workForm.getUseYn(), value -> findWork.setUseYn(value));

        if(creditsForm.getCreditsId() != null){
            for(int i = 0; i < creditsForm.getCreditsId().length; i++){
                Credits findCredits = creditsAdminRepository.findById(creditsForm.getCreditsId()[i])
                        .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));
                updateAttributeIfNotNull(getValueOrNull(creditsForm.getCreditsJob(),i), value -> findCredits.setJob(value));
                updateAttributeIfNotNull(getValueOrNull(creditsForm.getCreditsName(),i), value -> findCredits.setName(value));
            }
        }

        if (videoForm.getVideoId() != null) {
            for (int i = 0; i < videoForm.getVideoId().length; i++) {
                Video findVideo = videoAdminRepository.findById(videoForm.getVideoId()[i])
                        .orElseThrow(() -> new RestApiException(UserErrorCode.NO_RESULT));
                updateAttributeIfNotNull(getValueOrNull(videoForm.getVideoUrl(),i), value -> findVideo.setVideoUrl(value));
                updateAttributeIfNotNull(getValueOrNull(videoForm.getVideoType(),i) , value -> findVideo.setVideoType(value));
                updateAttributeIfNotNull(getValueOrNull(videoForm.getVideoTitle(),i) , value -> findVideo.setVideoTitle(value));
                updateAttributeIfNotNull(getValueOrNull(videoForm.getVideoContent(),i) , value -> findVideo.setVideoContent(value));
            }
        }



        return null;
    }

    private void alreadyFileParentsIdUpdate(WorkForm workForm, Work saveWork) {
        if(workForm.getFileId() != null){
            for (Long fileId : workForm.getFileId()) {
                fileService.filesWorkIdUpdate(saveWork.getId(),fileId);
            }
        }
    }

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

    private void workAddVideo(VideoForm videoForm, Work work) {
        if (videoForm.getOrd() != null) {
            for (int i = 0; i < videoForm.getOrd().length; i++) {
                Integer ord = videoForm.getOrd()[i];
                String url = getValueOrNull(videoForm.getVideoUrl(), i);
                String type = getValueOrNull(videoForm.getVideoType(), i);
                String title = getValueOrNull(videoForm.getVideoTitle(), i);
                String content = getValueOrNull(videoForm.getVideoContent(), i);

                work.addVideo(new Video(url, title, content, type, ord));
            }
        }
    }

    private String getValueOrNull(String[] array, int index) {
        return (array != null && index < array.length ) ? ("null".equals(array[index]) ? null : array[index]) : null;
    }

    private <T> void updateAttributeIfNotNull(String value, Consumer<String> setter) {
        if (value != null && !Objects.equals(value, "null")) {
            setter.accept(value);
        }
    }

}
