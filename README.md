[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion - CS 245 Project 02
Massive Motion is a Java simulation project that helps the user visualize a celestial motion using list data structures. These structures are custom built and read from configuration parameters from a property file.
These parameters are used to control simulation behavior, such as, window size, star and comet generation probability and anaimation timiing. These movement of celestial bodies are done in real time

-------------------------------------
#Project Overview
- Reads from a configuration data from a .txt file using java.util.Properties
- Implements the following list data structures:
       1. ArrayList = Array based implementation (with a fixed size of 10)
       2. LinkedList = Single Linked List
       3. DoublyLinkedList = Double Linked List (can go forward and backward)
       4. DummyHead= Linked list with a dummy head node
  -Create a window with an adjustable width and height
  -Has animation factors by using a timer
  -Generates and removes comets during the runtime of the program

   -------------------------------------
#Project Structure
MassiveMotion.java - Main class
CelestialObject.java - Represents a single celestial object
List.java- Interface for all list implementations
ArrayList.java - Array Based List
LinkedLis.javat- Singly linked list
DoublyLinkedList.java - Double Linked list
DummyHead.java - Linked list with dummy head
MassiveMotion.txt - Property configuration file
README.md - Project documentation

-----------
How to run the code: 
In terminal: java MassiveMotion MassiveMotion.txt

---------
Link to video implementation: https://drive.google.com/drive/folders/1jrZRfDYi_CEDZrzPkj277f0LwfqCzjl2?usp=sharing 
