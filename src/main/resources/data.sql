insert into patient (dob,email,bloodgroup,name)values
                                                  ('2000-04-24','mndhs@gmail.com','A_POSITIVE','mishra ndhs'),
                                                  ('2001-04-24','abdhs@gmail.com','B_POSITIVE','mishra abdhs'),
                                                    ('2015-04-24','hfhs@gmail.com','O_POSITIVE','mishra bfhf'),
                                                  ('2013-04-13','mhjhs@gmail.com','B_NEGATIVE','mishra mhjhs');
insert into Doctor(name,specilization,email)
values ("Rekha Singh","cardiology","rekh@123.gmail.com"),
       ("Shradha Dwiedi","Dermotology","shradha@gmail.com"),
       ("Ekta nair","Orthopedics","ekta@gmail.com"),
       ("Sonam Singh","Neurology","sonamsingh@gmail.com");
insert into Appointment(appointment_time,reason,doctor_id,patient_id)values
    ('2026-07-29 10:00:00','General Checkup','1','3'),
    ('2026-8-1 12:30:00','Skin Rash','2','2'),
    ('2026-8-3 12:30:00','Knee Pain','3','4'),
    ('2026-8-6 9:30:00','Stomach Pain','4','2'),
    ('2026-8-9 13:30:00','Consulatation','3','1');
