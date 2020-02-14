#!/usr/bin/env bash

#================================================================
# HEADER : calculator.sh
#================================================================
#% SYNOPSIS
#+    ${SCRIPT_NAME} <operation> <value1> <value2>
#+
SCRIPT_NAME=calculator.sh
#+
#% DESCRIPTION
#%    This is script converts xml files into a user friendly csv.
#%    It is intended to be used in conjucntion with CalculatorXML.jar
#%    and the Calculator3 Web Api as part of the Thunes Calculator Interview
#%    under the section Extras.
#%	
#% OPTIONS
#%
#%
#% EXAMPLES
#%    ${SCRIPT_NAME} 
#%
#================================================================
#- IMPLEMENTATION
#-    version         ${SCRIPT_NAME} 0.0.1
#-    author          Mark James Kiptubei
#-    copyright       
#-    license         
#-    script_id       00001
#-
#================================================================
#  HISTORY
#     2020/02/13 : mkiptubei : Script creation
#     
#================================================================
#  REQUIRED
#	Java 8+
#	CalculatorXml.jar , style.xsl , calculator.sh
#	Create a folder called Calculator3 in {$HOME} directory.
#	Copy calculator.sh , styl.xsl and CalculatorXML.jar
#	All 3 plus source code can be can be downloaded from git:
#	https://github.com/kiptubei/CalculatorXML.git
#
#================================================================
# END_OF_HEADER
#================================================================

#


# Paths
# -----------------------------------

scriptPath="../Calculator3"
TOMCAT_PATH="http://localhost:8080"
APP_PATH="Calculator3/start/calculate"

# Arithmetic Variables
# -----------------------------------
# All variables are initialized to zero.
# Modify these according to the calculation you wish to perform
#

VALUE1=$1
VALUE2=$2
VALUE3=$3

# Actions
# -----------------------------------
# All actions that the script can perform
# 

if [ "$1" == "-h" ]; then
  echo " script to add subtract multiply divide and factorial e.g Usage: "$SCRIPT_NAME" [add/ADD/a/A 9 0] [sub/SUB/s/S 5 1] [mul/m/MUL 2 6] [div/DIV/d/D 4 2] [fac/FAC/f/F 23]"
  exit 0
fi

# Actions
# -----------------------------------
# All actions that the script can perform
# 

 case $VALUE1 in
     add|ADD|[aA])
        VALUE1=add
	CALC="$VALUE1/$VALUE2/$VALUE3"
	echo $CALC
 	;;

     sub|SUB|[sS])
	VALUE1=sub
	CALC="$VALUE1/$VALUE2/$VALUE3"
        ;;
     mul|MUL|[mM])
  
        VALUE1=mul
	CALC="$VALUE1/$VALUE2/$VALUE3"
        ;;

     div|DIV|[dD])
  	
	VALUE1=div
	CALC="$VALUE1/$VALUE2/$VALUE3"
        ;;
     fac|FAC|[fF])
  
	VALUE1=fac
	CALC="$VALUE1/$VALUE2"
        ;;
     *)
 echo "Invalid input must be..."$SCRIPT_NAME" [add 2 3] or [fac 6] use add,sub,div,mul,fac"
 exit 0
 ;;
 esac



ADD="$VALUE1/$VALUE2/$VALUE3"
SUB="$VALUE1/$VALUE2/$VALUE3"
DIV="$VALUE1/$VALUE2/$VALUE3"
MUL="$VALUE1/$VALUE2/$VALUE3"
FAC="$VALUE1/$VALUE2"

# curl calculate
# -----------------------------------
# 
# This command passes the web api url to curl to perform the chosen
# action as defined above (ADD,SUB,DIV,MUL,FAC)
# Calculator 3 web api passes an xml to curl 
# You can edit the variables {$VALUE1} {$VALUE2} and actions {$ADD} to perform calculations safely 
#
# e.g
# <?xml version="1.0" encoding="UTF-8" standalone="yes"?><result><description>add</description><inputOne>4</inputOne><inputTwo>3<inputTwo><output>7.0</output></result>
#
# it strips the xml tags and writes them to file x3cut.xml
#
#uncomment this line if you want a new empty x3cut.xml and data3.xml file
#cat xempty.xml > x3cut.xml


curl $TOMCAT_PATH/$APP_PATH/$CALC | cut -c 56- >>  x3cut.xml 

#NOTE: UNCOMMENT AND MODIFY IF YOU WISH TO USE THIS FOR BULK CALCULATIONS AND COMMENT THE LINE ABOVE
#-------------------------------------------------------------------------------------------------------
#ADD="add/$VALUE2/$VALUE2"
#SUB="sub/$VALUE2/$VALUE3"
#DIV="div/$VALUE2/$VALUE3"
#MUL="mul/$VALUE2/$VALUE3"
#FAC="fac/$VALUE2"
#
#
#
#curl $TOMCAT_PATH/$APP_PATH/$FAC | cut -c 56- >>  x3cut.xml 
#curl $TOMCAT_PATH/$APP_PATH/$MUL | cut -c 56- >>  x3cut.xml
#curl $TOMCAT_PATH/$APP_PATH/$ADD | cut -c 56- >>  x3cut.xml 


# create result csv file
# -----------------------------------
# Here the xml created by operations are converted into a user friendly csv file for 
# easier manipulation by common desktop applictions e.g MS Office,LibreOffice suite
# 
#
#  ## NOTE: UNCOMMENT OUT THIS SECTION ONLY WHEN YOU ARE 
#  ##	   WANT TO GET THE CSV RECORD OF ALL YOU CALCULATIONS.
#  ##      OUTPUT IS IN THE PROJECT DIRECTORY WHERE Calculator3 FILES
#  ##      AND CalculatorXML.jar IS LOCATED 

# append tag to the beggining and end of the file for proper XML style formating
cp x3cut.xml data3.xml
sed -i '1i<host>' data3.xml
echo '</host>' >> data3.xml

echo  $scriptPath
java -jar $scriptPath/CalculatorXml.jar

cat x3.csv

