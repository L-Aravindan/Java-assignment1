# Design Notes - Learntrack

(If u have already read the previous one , just to remind you this one is also not AI generated btw :)))) )

1 . Why ArrayList instead of Array?
ANSWER :

to simply answer this , array is not dynamic and it requires a fixed size , but arraylist is dynamic and doesnt require a anything like that  , here i can decide to add n number of students or courses or enrollments for all these to happen I will be needing something dynamic and that dynamic thing is ArrayList

---


2.where and why static members are used ?
ANSWER:

static members are mainly used in the 'IDGenerator' utility class

The ID counters are static because they represent shared state across the application.Every time a new student, course, or enrollment is created, the ID should be unique, regardless of which object or service creates it. Using static methods avoids creating unnecessary objects and keeps ID generation centralized and consistent.

---

3.Where inheritance is used and what it helped with??
ANSWER:

Inheritance is used b/w the person and student classes 

the person class contains common fields like name and email , student extend person and adds student specific fields such as batch and active status.

this helped avoid duplication of common fields and made the code easier to maintain.