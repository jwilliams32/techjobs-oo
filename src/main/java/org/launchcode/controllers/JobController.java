package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.data.JobData;
import org.launchcode.models.forms.JobForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    //The /{id} is what will be returned as the path it will be job/{id}
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        model.addAttribute("job", jobData.findById(id));

        // TODO #1 - get the Job with the given ID and pass it into the view

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
       if(errors.hasErrors()){
           model.addAttribute("name", "Add Job");
           return "new-job";
       }

            String aName = jobForm.getName();
        System.out.print(aName);
            Employer aEmployer = jobData.getEmployers().findById(jobForm.getEmployerId());
        System.out.print(aEmployer);
            Location aLocation = jobData.getLocations().findById(jobForm.getLocationId());
        System.out.print(aLocation);
            PositionType aPositionType = jobData.getPositionTypes().findById(jobForm.getPositionTypeId());
        System.out.print(jobForm.getLocationId());
        System.out.print(aPositionType);
            CoreCompetency aSkill = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId());
        System.out.print(aSkill);

            Job newJob = new Job(aName,
                    aEmployer,
                    aLocation,
                    aPositionType,
                    aSkill);
        System.out.print(newJob);
//            (jobForm.getEmployers(jobForm.getEmployer(jobForm.getEmployerId())));
//            newJob.setName(aName);
//            newJob.setEmployer(aEmployer);
//            newJob.setLocation(aLocation);
//            newJob.setPositionType(aPositionType);
//            newJob.setCoreCompetency(aSkill);
//           ;
//        for(newJob.getEmployer(): newJob.get)
//
//        {
//            System.out.println(newJob.getId());
//        }




//            model.addAttribute("name",jobForm.getName());
//            model.addAttribute("employerID",jobForm.getEmployerId());

//            model.addAttribute("location", jobForm.getLocation());
//            model.addAttribute("coreCompetencyId", jobForm.getCoreCompetency());
//            model.addAttribute("positionType", jobForm.getPositionType());
            jobData.add(newJob);

//            new Job(String aName, Employer aEmployer, Location aLocation,
//                    PositionType aPositionType, CoreCompetency aSkill);
//            model.addAttribute("name", String aName);
//            jobData.add(job);

        return "redirect:/job?id=" + newJob.getId();

    }
}
