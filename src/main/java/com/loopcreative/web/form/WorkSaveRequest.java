package com.loopcreative.web.form;

import lombok.Data;

import java.util.List;

@Data
public class WorkSaveRequest {

    private List<VideoForm> videoForms;
    private WorkForm workForm;
    private List<CreditsForm> creditsForms;
    private List<FileForm> saveFileForms;
    private FileForm thumbnailFileForm;
    private List<Long> removeFileForms;
}
