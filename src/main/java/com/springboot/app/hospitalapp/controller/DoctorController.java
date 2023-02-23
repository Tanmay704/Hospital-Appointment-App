package com.springboot.app.hospitalapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.entity.FileDB;
import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.entity.PatientDoctor;
import com.springboot.app.hospitalapp.service.DoctorService;
import com.springboot.app.hospitalapp.service.FilesService;
import com.springboot.app.hospitalapp.service.PatientDoctorService;
import com.springboot.app.hospitalapp.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private PatientDoctorService patientDoctorService;
	@Autowired
	private FilesService filesService;
	@GetMapping()
	public String getDoctor(Model model){
		model.addAttribute("date", new java.util.Date());
      	//	return "patientLogin";
		return null;
      		
	}
	@GetMapping("/dashboard")
	 
	public String dashboardPatient(@ModelAttribute Patient patient, Model model) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	      model.addAttribute("username", auth.getName());
	      System.out.print("USERNAME :" + auth.getName());
		return "doctorDashboard";
		
	}
	@PostMapping("/add")
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		doctorService.save(doctor);
		return doctor;
	}

	@GetMapping("/login")
	 
	public String loginDoctor() {
	
		return "doctorLogin";
		
	}
	@GetMapping("/approveAppointment")
	 
	public String loginPatient(Model model) {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Doctor doctor = doctorService.findByUserName(auth.getName());
		List <PatientDoctor>patientDoctorList = patientDoctorService.findAllByDId(doctor.getdId());

		model.addAttribute("patientDoctorList", patientDoctorList);

		return "approveAppointment";
		
	}
	 @GetMapping("/downloadfile/{id}")
	 public ResponseEntity<byte[]> downloadFile(@PathVariable("id") int appoNo , Model model, HttpServletResponse response) throws IOException {
	  PatientDoctor temp = patientDoctorService.findByAppointmentNo(appoNo);

	  
	    FileDB fileDB = filesService.findById(temp.getFileDB().getId());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  
	  
	  }
	 
	@GetMapping("/approve/{id}")
	 
	public String approveAppointment(@PathVariable("id") int appointmentNo, @ModelAttribute PatientDoctor patientDoctor, RedirectAttributes redirAttrs) {
	
		    patientDoctorService.updateStatus(appointmentNo, 1);
		    redirAttrs.addFlashAttribute("approvedSuccess", "Success ! Appointment approved");
		return "redirect:/doctor/approveAppointment";
		
	}
	@GetMapping("/decline/{id}")
	 
	public String declineAppointment(@PathVariable("id") int appointmentNo, @ModelAttribute PatientDoctor patientDoctor, RedirectAttributes redirAttrs) {
	
		    patientDoctorService.updateStatus(appointmentNo, 0);
		    redirAttrs.addFlashAttribute("declinedSuccess", "Success ! Appointment Declined");
		return "redirect:/doctor/approveAppointment";
		
	}
	
	@GetMapping("/doctorPatientProfile/{id}")
	 
	public String doctorPatientProfile(@PathVariable("id") Integer pId, Model model) {
	      System.out.println(pId);
		Patient patient = patientService.findByPId(pId);
		model.addAttribute("patient",patient);
		return "doctorPatientProfile";
		
	}
	@GetMapping("/appointmentHistory")
	 
	public String appointmentHistory(@PageableDefault(size = 4) Pageable pageable, Model model, @RequestParam(name = "status", defaultValue = "-2") Integer status, String keyword) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 Date  today= new Date();
		  DateFormat f = new SimpleDateFormat("yyyy-M-dd hh:mm");
		 	String date = f.format(today);
	
		    Doctor doctor = doctorService.findByUserName(auth.getName());
		    Page <PatientDoctor>patientDoctorList;
		    
		    if(keyword != null)
		    {
		    	patientDoctorList = (patientDoctorService.findByKeyword(doctor.getdId(), keyword.trim()	, date, pageable));
		    	model.addAttribute("patientDoctorList", patientDoctorList);
		    	return "doctorAppointmentHistory";
		    }
			if(status != -2) {
				patientDoctorList = (patientDoctorService.findAllByStatus(doctor.getdId(),status, date, pageable));
			
			}else {
				patientDoctorList = (patientDoctorService.findAllBydIdHistory(doctor.getdId(), date, pageable));

			}
		
			model.addAttribute("patientDoctorList", patientDoctorList);
		return "doctorAppointmentHistory";
		
	}
	
	
	@GetMapping("/upcomingAppointment")
	 
	public String upcomingAppointment(@PageableDefault(size = 2) Pageable pageable, Model model, @RequestParam(name = "status", defaultValue = "-2") Integer status, String keyword) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 Date  today= new Date();
		  DateFormat f = new SimpleDateFormat("yyyy-M-dd hh:mm");
		 	String date = f.format(today);
	
		    Doctor doctor = doctorService.findByUserName(auth.getName());
		    Page <PatientDoctor>patientDoctorList;
		    
		    if(keyword != null)
		    {
		    	patientDoctorList = (patientDoctorService.findByUpcomingKeyword(doctor.getdId(), keyword.trim()	, date, pageable));
		    	model.addAttribute("patientDoctorList", patientDoctorList);
		    	return "doctorAppointmentHistory";
		    }
			if(status != -2) {
				patientDoctorList = (patientDoctorService.findAllUpcomingByStatus(doctor.getdId(),status, date, pageable));
			
			}else {
				patientDoctorList = (patientDoctorService.findAllUpcomingBydIdHistory(doctor.getdId(), date, pageable));

			}
		
			model.addAttribute("patientDoctorList", patientDoctorList);
		return "upcomingAppointment";
		
	}
}
