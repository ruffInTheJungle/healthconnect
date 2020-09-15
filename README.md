# Healthconnect
HealthConnect is a simple healthcare app build entirely for education purposes. The application represents a healthcare management system with simple functionalities.
The users could be registered with the following roles: patient, doctor, admin. Each of the roles has relevant functionalities:
- Patients can raise appointment requests to different doctors and review a prescriptions that they may have received after attending an appointment.
- Doctors can manage the appointments raised to them by the patients and after finalizing the appointment workflow could choose to archive the appointment directly or issue a prescription and than archive the appointment.
- The administrator can manage the user roles and has acccess to all functionalities.

# Technologies
For the purposes of this project I have implemented the following technologies: Spring Boot, Spring Data, Spring Security, Web Controllers, Thymeleaf, Bootstrap, jQuery. The user authentication and authorization are entirely managed through Spring Security. The application could be dockerized with minimum configurations thanks to the pre-configured Dockerfile and docker-compose file.
