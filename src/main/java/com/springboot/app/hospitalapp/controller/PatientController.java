	package com.springboot.app.hospitalapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.hospitalapp.entity.Doctor;
import com.springboot.app.hospitalapp.entity.FileDB;
import com.springboot.app.hospitalapp.entity.Patient;
import com.springboot.app.hospitalapp.entity.PatientDoctor;
import com.springboot.app.hospitalapp.entity.Patientaddress;
import com.springboot.app.hospitalapp.exceptionHandler.MobNoExistException;
import com.springboot.app.hospitalapp.service.DoctorService;
import com.springboot.app.hospitalapp.service.FilesService;
import com.springboot.app.hospitalapp.service.PatientAddressService;
import com.springboot.app.hospitalapp.service.PatientDoctorService;
import com.springboot.app.hospitalapp.service.PatientService;


@Controller
@RequestMapping("/patient")

public class PatientController {

	@Value("{$spring.servlet.multipart.max-file-size}")
	private String maxFileSize;
	@Autowired
	PatientService patientService;
	@Autowired
	DoctorService doctorService;
	@Autowired
	public PatientAddressService patientAddressService;
	@Autowired
	public PatientDoctorService patientDoctorService;
	@Autowired
	public FilesService filesService;
	
	public Patient patient;
	public Patientaddress patientAddress;

	@PostMapping("/add")
	public String createPatient(@ModelAttribute("patient") @Valid Patient patient,Errors errors, @ModelAttribute("patientaddress")  @Valid Patientaddress patientAddress,
			Errors addressError, Model model, RedirectAttributes redirAttrs) throws MobNoExistException {

//		   System.out.println(errors);)
		Patient checkPatient = patientService.findByMobNo(patient.getMobNo());
		if(!(checkPatient == null))
		{
//			throw new MobNoExistException("Mob no already exist in database");
			model.addAttribute("error", "The mobile number is already Present; please enter a new one. ");
			 return "patientRegister";
		}

		   if ((null != errors && errors.getErrorCount() > 0)) {
			 

	            return "patientRegister";
	        }else {
	     model.addAttribute("patient", patient);
	     this.patient = patient;
	     this.patientAddress = patientAddress;}
		  
		return "setPassword";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<Patient> listPatient() {
	
		return patientService.findAll();
		
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String postListPatient( @RequestParam("password1")String password1, @RequestParam("password2")String password2,
			@ModelAttribute Patient patient, Model model, RedirectAttributes redirAttrs) {
		

	  
	  	
	    if(password1.equals(""))
	    {
	    	model.addAttribute("nullError", "Failed ! Password can't be Null");
	    	return "setPassword";
	    	
	    }
	    if(!password1.equals(password2))
	    {
	    	model.addAttribute("notSameError", "Failed ! Password Mismatch");
	    	return "setPassword";
	    	
	    }
	    
	    //validate password
	   String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	   if(!password1.matches(pattern))
	   {
		 	model.addAttribute("notMatchError", "Failed ! Password not matching requirements");
	    	return "setPassword";
	   }
	   
	    this.patient.setPassword(password1);
	    this.patient.setPatientAddress(this.patientAddress);
		 patientService.save(this.patient);
		
		 redirAttrs.addFlashAttribute("success", "Register successfully");

		 return "redirect:/patient/login";
		  //return null;
		
	}
	@GetMapping("/login")
	 
	public String loginPatient() {
	
		
		return "patientLogin";
		
	}

//	@GetMapping("/logout")
//	 
//	public String logoutatient() {
//	
//		return "redirect:patient/login";
//		
//	}
	@GetMapping("/register")
	 
	public String getRegisterForm(@ModelAttribute Patient patient) {
	
		return "patientRegister";
		
	}
	@GetMapping("/pSetting")
	 
	public String patientSetting(Model model) {

		
		  
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = patientService.findByMobNo(auth.getName());
		model.addAttribute("patient", patient);
//	    Patientaddress ad = patient.getPatientAddress();
		//System.out.println(ad.getCity());
		return "pSetting";
		
	}
	
	@GetMapping("/editPatientProfile")
	public String editPatientProfile(Model model) {

		  
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Patient patient = patientService.findByMobNo(auth.getName());
		model.addAttribute("patient", patient);
//	    Patientaddress ad = patient.getPatientAddress();
	
		return "editPatientProfile";
		
	}
	@PostMapping("/pSetting")
	public String editPatient(@ModelAttribute Patient updatedPatient) {



		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Patient patient = patientService.findByMobNo(auth.getName());
	
		patient.setAge(updatedPatient.getAge());
		patient.setEmail(updatedPatient.getEmail());
		patient.setFullName(updatedPatient.getFullName());
		patient.setHeight(updatedPatient.getHeight());
		patient.setWeight(updatedPatient.getWeight());
		Patientaddress address =  patient.getPatientAddress();
		Patientaddress updatedAddress = updatedPatient.getPatientAddress();
		address.setCity(updatedAddress.getCity());
		address.setStreet(updatedAddress.getStreet());
		address.setZipCode(updatedAddress.getZipCode());
		address.setState(updatedAddress.getState());
		patient.setOccupation(updatedPatient.getOccupation());
        patient.setPatientAddress(address);
		patientService.save(patient);
		 
//		 if(ad.getCity() != null)
//		 {System.out.println(ad.getCity());}
		return "patientDashboard";
		
	}
	@PostMapping("/register") 
	 
	public String registerPatient(@Valid Patient patient) {
	
		return "patientRegister";
		
	}


	@GetMapping("/dashboard")
	 
	public String dashboardPatient(@ModelAttribute Patient patient, Model model) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  	      model.addAttribute("mobNo", auth.getName());
		return "patientDashboard";
		
	}
	
	@GetMapping("/bookAppointment")
	 
	public String bookAppointment(Model model) {
	
		List <Doctor>doctorList = doctorService.findAll();
		model.addAttribute("doctorList", doctorList);
		
		return "listDoctor";
		
	}
	@GetMapping("/appointment/{id}")
	 
	public String appointment(@PathVariable("id") int doctorId, Model model, @ModelAttribute PatientDoctor patientDoctor) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Patient patient = patientService.findByMobNo(auth.getName());
		    Doctor doctor = doctorService.findById(doctorId);
			model.addAttribute("patient", patient);
			model.addAttribute("doctor", doctor);
			
	
		return "appointment";
		
	}
	@PostMapping("/appointment/{id}")

	public String appointmentSave(@RequestParam("file")MultipartFile file, @PathVariable("id") int doctorId, Model model, @ModelAttribute PatientDoctor patientDoctor, RedirectAttributes redirAttrs) 
			throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Patient patient = patientService.findByMobNo(auth.getName());
	    Doctor doctor = doctorService.findById(doctorId);
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		patientDoctor.setStatus(-1);
      
		if(!file.isEmpty()) {
//			System.out.println(file.getContentType());
			String fType = file.getContentType();
			if(!(fType.equalsIgnoreCase("application/pdf") || fType.equalsIgnoreCase("image/jpeg") ||  fType.equalsIgnoreCase("image/png") ||
					fType.equalsIgnoreCase("application/x-zip-compressed") || fType.equalsIgnoreCase("application/octet-stream"))) {
				redirAttrs.addFlashAttribute("declinedType", "Wrong file type, please upload correct file");

				 model.addAttribute("id", doctorId);
				return "redirect:/patient/appointment/{id}";
			}
			
			
		
				
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
			patientDoctor.setFileDB(fileDB);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		}
		
		

		
		
      //for checking other doctor appointment
      List<String> appointmentList = patientDoctorService.findbyDid(doctorId);

      List<PatientDoctor> patientAppointmentList = patientDoctorService.findByPId(patient.getPId());

      
      DateFormat htmlFormater = new SimpleDateFormat("yyyy-M-dd'T'hh:mm");
      DateFormat f = new SimpleDateFormat("yyyy-M-dd hh:mm");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
      DateFormat timeFormat = new SimpleDateFormat("hh:mm");
      Date d1,d2;
      boolean flag = false;
      
      if(!patientAppointmentList.isEmpty()) {
    	  for(PatientDoctor pd : patientAppointmentList) {
    		  try {
    			  d1 = f.parse(pd.getAppointmentTime());
				d2 = htmlFormater.parse(patientDoctor.getAppointmentTime());
				if((dateFormat.format(d1)).compareTo(dateFormat.format(d2)) == 0) {
					 redirAttrs.addFlashAttribute("patientError", "Failed ! You can schedule only one appointment for a day");
					  return "redirect:/patient/dashboard";	  
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  }
      }else {
    	  flag = true;
      }
      
      if(!appointmentList.isEmpty()) {
    	  for(String time : appointmentList)
    	  	{
    		    try {
				 d1 = f.parse(time);
				 d2 = htmlFormater.parse(patientDoctor.getAppointmentTime());
				 
				 if((dateFormat.format(d1)).compareTo(dateFormat.format(d2)) == 0) {
					 
					 Calendar cal = Calendar.getInstance();
					 cal.setTime(d1);
					 cal.add(Calendar.MINUTE, 15);
					 String newTime = timeFormat.format(cal.getTime());
				  if(timeFormat.format(d1).compareTo(timeFormat.format(d2)) <= 0 && newTime.compareTo(timeFormat.format(d2)) >= 0)
				  { 
//					
					  redirAttrs.addFlashAttribute("error", "Failed ! Please Reschdeule appointment With different Time slot ");
					  return "redirect:/patient/dashboard";	  
				 }else {flag = true;}
				 
//				  System.out.println(timeFormat.format(d1));
				
			}else 
				{flag = true;}
			
    		  } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	  	}}else {  flag = true;
    	  	}
    	  		
    	  
      
		if(flag) {redirAttrs.addFlashAttribute("success", "Success ! Your Appointement Booked");
		patientDoctorService.save(patientDoctor); }
		
		 return "redirect:/patient/dashboard";
	}
	
	
	
	@GetMapping("/deletePatient")
	 
	public String deletePatient() {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
		  Patient patient = patientService.findByMobNo(auth.getName());

		  patientDoctorService.deleteById(patient.getPId());
		  patientService.deleteById(patient.getPId());
		return "redirect:/patient/login";
		
	}
	
	 
	 @GetMapping("/appointmentHistory")
	
	public String appointmentHistory(Model model, @ModelAttribute PatientDoctor patientDoctor) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    Patient patient = patientService.findByMobNo(auth.getName());
		    List <PatientDoctor> patientDoctorList = patientDoctorService.findByPId(patient.getPId());
		model.addAttribute("patientDoctorList", patientDoctorList);
		return "appointmentHistory";
		
	}
	
}
