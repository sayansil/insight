# Project Insight

## BRIEF DESCRIPTION

Project Insight aims to completely automate the problem of surveillance. It requires a CCTV camera connected to a computer with a mid-ranged GPU and an active internet connection. The live feed obtained from the CCTV camera is received by the computer which performs real-time object detection using the YOLO algorithm. The objects detected are documented as a list with their respective timestamps and sent to the users at certain time intervals through an Android app. The users can be anywhere in the world and can view the documentation which is refreshed at intervals set by him. If any harmful (or blacklisted) object is detected, an emal with the snapshot is automatically sent to the user. In the next version, we are also working on a way to store all the items in a centralized database so that the user can view the history of the items detected in a particular timespan.

<iframe src="https://player.vimeo.com/video/344358614" width="640" height="360" frameborder="0" allow="autoplay; fullscreen" allowfullscreen></iframe>

## TECHNICAL FOUNDATION

We have used a lot of concepts and programming languages to implement project Insight. For real-time object detection, the program should run extremely fast and process images very rapidly. So here for deep learning, we have used C and C++ to implement the Convolutional Neural Networks. Java has been used for networking, for communication with the centralized server and also for creating the Android app, Python has been used for generating continuous keypress events at intervals set by the user and to glue all the modules together, a bit of bash scripting was also required.

## WHAT PROBLEM THE PROJECT SOLVES?

Project Insight completely automates the surveillance of buildings and homes and other places. It completely removes the need for human intervention. In traditional CCTV surveillance, the feed has to be continuously monitored by a human and some slight loss of concentration might result in a huge loss for the organisation. This is where project Insight comes in. Moreover, since Insight uses Deep Learning for real-time object detection it can also be tuned for much better performance in specific use-cases, for eg. in an automobile factory, Insight might have to detect multiple varieties of cars and the user might want to have customized labels like Suzuki, Tata, Audi etc instead of just the car label. In such scenarios, the Deep Learning model can be retrained on a dataset of the cars present in that particular car factory.
