Integration between Java components and the Ocean EhrService by Web Services 

BUILD
---- 
Ant based built script, need to include the usual kernel jars to build.
Apache Axis 1_4 needs to be installed and the path to Axis should be correctly
configured in the build script.

RUN
---
Type 'ant run' to invoke the EhrSerfice Tester and find the log files under
log directory.


TODO
----
The conversion logic for kernel objects is one way - from kernel to XML binding. 
But it will be finished and extracted as a stand-alone component.
