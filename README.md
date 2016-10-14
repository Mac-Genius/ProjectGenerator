# ProjectGenerator

A simple project generator for when you want to work on a programming problem
on Kattis or another code site of you choice. It currently generates only .java
and C++ template files.

## Usage

Just execute the generator with the following command:

`java -jar ProjectGenerator.jar <project name> <args>`

### Arguments

* -h | -help : help menu
* -s | -size \<example file amount\> : the amount of example files to create. Defaults to 2
* -t | -template \<template\> : the template to use. Defaults to java_default
* -kattis : fetches the problems from Kattis and creates a makefile and test script for them
* -local : fetches nothing and creates empty example files

### Templates

* java_default - this is the default if no template is specified
* cpp_default - the default C++ template