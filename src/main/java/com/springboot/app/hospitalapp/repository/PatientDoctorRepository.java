package com.springboot.app.hospitalapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.hospitalapp.entity.PatientDoctor;


public interface PatientDoctorRepository extends PagingAndSortingRepository<PatientDoctor, Integer> {


//	PatientDoctor findBydId(int id);
	@Query("SELECT appointmentTime FROM PatientDoctor pd WHERE pd.doctor.dId = (:dId)")
    public List<String> findbyDid(@Param("dId") int dId);
	
	@Query("FROM PatientDoctor pd WHERE pd.appointmentNo = (:id)")
	 public PatientDoctor findById(@Param("id") int id);
	@Modifying
	@Transactional
	@Query("DELETE FROM PatientDoctor pd WHERE patient.pId = (:pId)")
    public void deleteByPId(@Param("pId") int PId);

	
	@Query("FROM PatientDoctor pd WHERE pd.patient.pId = (:pId)")
    public List<PatientDoctor> findbyPid(@Param("pId") int pId);
	
	@Query("FROM PatientDoctor pd WHERE pd.doctor.dId = (:dId) AND pd.status = -1")
	public List<PatientDoctor> findAllByDId(@Param("dId") int dId);
	
	@Query("FROM PatientDoctor pd WHERE pd.doctor.dId = (:dId)")
	public List<PatientDoctor> findAllBydId(@Param("dId") int dId);
	
	@Query("FROM PatientDoctor pd WHERE pd.doctor.dId = (:dId) AND pd.appointmentTime < (:today) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findAllBydIdHistory(@Param("dId") int dId, @Param("today") String today, Pageable pageable);
	
	@Query("FROM PatientDoctor pd WHERE pd.status = (:status) AND (pd.appointmentTime < (:today) AND pd.doctor.dId = (:dId)) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findAllByStatus(@Param("dId") int dId, @Param("status") int status, @Param("today") String today, Pageable pageable);
	
	//searching
	@Query("FROM PatientDoctor pd WHERE (pd.patient.fullName LIKE %:keyword% OR pd.appointmentTime LIKE %:keyword%) AND pd.doctor.dId = (:dId) AND (pd.appointmentTime < (:today)) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findByKeyword(@Param("dId") int dId, @Param("keyword") String keyword, @Param("today") String today, Pageable pageable);
	
	
	//future appointment
	
	@Query("FROM PatientDoctor pd WHERE pd.doctor.dId = (:dId) AND pd.appointmentTime >= (:today) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findAllUpcomingBydIdHistory(@Param("dId") int dId, @Param("today") String today, Pageable pageable);
	
	@Query("FROM PatientDoctor pd WHERE pd.status = (:status) AND (pd.appointmentTime >= (:today) AND pd.doctor.dId = (:dId)) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findAllUpcomingByStatus(@Param("dId") int dId, @Param("status") int status, @Param("today") String today, Pageable pageable);
	
	//searching
	@Query("FROM PatientDoctor pd WHERE (pd.patient.fullName LIKE %:keyword% OR pd.appointmentTime LIKE %:keyword%) AND pd.doctor.dId = (:dId) AND (pd.appointmentTime >= (:today)) ORDER BY pd.appointmentTime ASC")
	public Page<PatientDoctor> findByUpcomingKeyword(@Param("dId") int dId, @Param("keyword") String keyword, @Param("today") String today, Pageable pageable);
	
	
	
	
	@Modifying
	@Transactional
	@Query("UPDATE PatientDoctor pd SET pd.status = (:status) WHERE pd.appointmentNo = (:aId)")
    public void updateStatus(@Param("aId") int aId, @Param("status") int status);
}
