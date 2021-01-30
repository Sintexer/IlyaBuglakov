# Raise

Raise is a service aimed to improve everyday user experience in the sphere of studying and
knowledge acquisition.

## Table of contents
___
## Introduction
Raise is a service where you can either create or pass tests. Community created content
is being moderated, so you won't see any obscene content. Also, there are comment
sections for each test, so you can share your opinion with other users.

Each test is specified by an arbitrary set of four characteristics:
- Memory
- Logic
- Calculations
- Reaction

So, when you pass the test, result will affect the performance of your personal
characteristics set. This is how statistics are kept. Statistics cen be viewed in 
user profile.

___

### General info

Time for passing the test is not limited. After the end of testing user will see its result and 
questions, where he made a mistake. Even semi-correct answers can bring points.

Subject rights in applications are specified by the set of permissions. For example, 
to confirm the test, you must have "confirm:test" permission. Subject permissions set
is known from subjects roles set. Each role has specified suite of permissions. 

#### Roles:
- Guest 
    - Can pass tests, but result won't be saved. 
    - Can view comments and user profiles.
    - Can log in or register anytime. 
    - Don't have access to test creator.
- User 
    - Сan pass test, result will be saved. Each time when user passes the same test, 
  new result will be saved only if it's greater than previous. 
    - Сan write comments. 
    - Сan see other users profiles and edit his own profile. 
    - Have access to test creator. After adding the test is sent for moderation. Maximum user can have 3 unapproved
  tests simultaneously.
- Admin 
  - Has all permissions in application.
  - Can ban comments.
  - Has access to 'admin panel', where he can
    see all new tests and confirm or ban them. 
  - Status of test created by an admin
    is 'CONFIRMED' by default. 
