package af.gov.anar.lib.jobs.api;


import af.gov.anar.lib.jobs.instance.CronJob;
import af.gov.anar.lib.jobs.instance.SimpleJob;
import af.gov.anar.lib.jobs.service.JobSchedularService;
import af.gov.anar.lib.jobs.util.SchedularServerResponseCode;
import af.gov.anar.lib.jobs.util.ServerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

	@Autowired
	JobSchedularService jobService;

	ObjectMapper mapper = new ObjectMapper();

	@PostMapping("/schedule")
	public ServerResponse schedule(@RequestBody(required = true) String data) throws IOException {

		// @RequestParam("jobName") String jobName, 
		// 	@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
		// 	@RequestParam("cronExpression") String cronExpression

		System.out.println("JobData => "+ data);

		System.out.println("JobController.schedule()");

		JsonNode root  = mapper.readTree(data);
		String jobName  = root.get("jobName").asText();
		String cronExpression = root.get("cronExpression").asText();
		String date = root.get("jobScheduleTime").asText();
		Date jobScheduleTime= new Date(date);

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(SchedularServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}

		//Check if job Name is unique;
		if(!jobService.isJobWithNamePresent(jobName)){

			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = jobService.scheduleOneTimeJob(jobName, SimpleJob.class, jobScheduleTime);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, jobService.getAllJobs());
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}
				
			}else{
				//Cron Trigger
				boolean status = jobService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, jobService.getAllJobs());
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}				
			}
		}else{
			return getServerResponse(SchedularServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
		}
	}

	@RequestMapping("/unschedule")
	public void unschedule(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.unschedule()");
		jobService.unScheduleJob(jobName);
	}

	@RequestMapping("/delete")
	public ServerResponse delete(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.delete()");		

		if(jobService.isJobWithNamePresent(jobName)){
			boolean isJobRunning = jobService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = jobService.deleteJob(jobName);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}
			}else{
				return getServerResponse(SchedularServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}
		}else{
			//Job doesn't exist
			return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("/pause")
	public ServerResponse pause(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.pause()");

		if(jobService.isJobWithNamePresent(jobName)){

			boolean isJobRunning = jobService.isJobRunning(jobName);

			if(!isJobRunning){
				boolean status = jobService.pauseJob(jobName);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}			
			}else{
				return getServerResponse(SchedularServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
		}		
	}

	@RequestMapping("/resume")
	public ServerResponse resume(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.resume()");

		if(jobService.isJobWithNamePresent(jobName)){
			String jobState = jobService.getJobState(jobName);

			if(jobState.equals("PAUSED")){
				System.out.println("Job current state is PAUSED, Resuming job...");
				boolean status = jobService.resumeJob(jobName);

				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, true);
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}
			}else{
				return getServerResponse(SchedularServerResponseCode.JOB_NOT_IN_PAUSED_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("/update")
	public ServerResponse updateJob(@RequestParam("jobName") String jobName, 
			@RequestParam("jobScheduleTime") @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm") Date jobScheduleTime, 
			@RequestParam("cronExpression") String cronExpression){
		System.out.println("JobController.updateJob()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(SchedularServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}

		//Edit Job
		if(jobService.isJobWithNamePresent(jobName)){
			
			if(cronExpression == null || cronExpression.trim().equals("")){
				//Single Trigger
				boolean status = jobService.updateOneTimeJob(jobName, jobScheduleTime);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, jobService.getAllJobs());
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}
				
			}else{
				//Cron Trigger
				boolean status = jobService.updateCronJob(jobName, jobScheduleTime, cronExpression);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, jobService.getAllJobs());
				}else{
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}				
			}
			
			
		}else{
			return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@RequestMapping("/jobs")
	public ServerResponse getAllJobs(){
		System.out.println("JobController.getAllJobs()");

		List<Map<String, Object>> list = jobService.getAllJobs();
		return getServerResponse(SchedularServerResponseCode.SUCCESS, list);
	}

	@RequestMapping("/checkJobName")
	public ServerResponse checkJobName(@RequestParam("jobName") String jobName){
		System.out.println("JobController.checkJobName()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(SchedularServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}
		
		boolean status = jobService.isJobWithNamePresent(jobName);
		return getServerResponse(SchedularServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("/isJobRunning")
	public ServerResponse isJobRunning(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.isJobRunning()");

		boolean status = jobService.isJobRunning(jobName);
		return getServerResponse(SchedularServerResponseCode.SUCCESS, status);
	}

	@RequestMapping("/jobState")
	public ServerResponse getJobState(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.getJobState()");

		String jobState = jobService.getJobState(jobName);
		return getServerResponse(SchedularServerResponseCode.SUCCESS, jobState);
	}

	@PostMapping("/stop")
	public ServerResponse stopJob(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.stopJob()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(jobService.isJobRunning(jobName)){
				boolean status = jobService.stopJob(jobName);
				if(status){
					return getServerResponse(SchedularServerResponseCode.SUCCESS, true);
				}else{
					//Server error
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}

			}else{
				//Job not in running state
				return getServerResponse(SchedularServerResponseCode.JOB_NOT_IN_RUNNING_STATE, false);
			}

		}else{
			//Job doesn't exist
			return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
		}
	}

	@PostMapping(value = "/start")
	public ServerResponse startJobNow(@RequestParam("jobName") String jobName) {
		System.out.println("JobController.startJobNow()");

		if(jobService.isJobWithNamePresent(jobName)){

			if(!jobService.isJobRunning(jobName)){
				boolean status = jobService.startJobNow(jobName);

				if(status){
					//Success
					return getServerResponse(SchedularServerResponseCode.SUCCESS, true);

				}else{
					//Server error
					return getServerResponse(SchedularServerResponseCode.ERROR, false);
				}

			}else{
				//Job already running
				return getServerResponse(SchedularServerResponseCode.JOB_ALREADY_IN_RUNNING_STATE, false);
			}

		}else{
            //Job doesn't exist
            return getServerResponse(SchedularServerResponseCode.JOB_DOESNT_EXIST, false);
        }
	}

	public ServerResponse getServerResponse(int responseCode, Object data){
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse; 
	}
}