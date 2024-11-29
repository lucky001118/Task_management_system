package com.lucky.service;

import com.lucky.model.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submissionTask(Long taskId, String githubLink,Long userId,String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmissions();

    List<Submission> getTaskSubmissionsByTaskId(Long taskId);

    Submission acceptDeclineSubmission(Long id,String status) throws Exception;

}
