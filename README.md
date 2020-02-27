# Submission DSpace #

Application that delivers data from DSpace into Rosetta. New items will handled as ingests. Updates make use of the AIP (Archival Information Package) update webservice in Rosetta. A queue table holds all items that should either be inserted as new SIP into Rosetta or updated an existing IE in Rosetta. All relevant files are located on an SFTP storage location. A cleanup job will regularly remove all processed items from SFTP and remove all duplicate (full) updates.

## Current Version 1.2 ##

## TODO

* Javadoc
* create release for github

## History ##

### version 1.2 ###

* application renamed from 'aip-update' to 'submissionDs' to properly show what the application is for
* remove all duplicate 'FEEDER_UPDATED' with the same SIP_NAME from SFTP
* add timestamp update for every queue update

### version 1.1.5 ###
* DDE-415, unique constraint processing when ingesting new items

### version 1.1.4 ###
* DDE-605, file extension is read again, but a warning is thrown (no exception), if a file has no extension

### version 1.1.3 ###
* DDE-605, file extension extract removed
* classpath fixed

### version 1.1.2 ###
* added waiting timer after each update
* extented properties by control.update.waiting

### version 1.1.1 ###
* added REJECTED handling
* implemented error log for error-less errors

### version 1.1 ###
* implementation deleted items
* Deleted business domain

### version 1.0 ###
* cleanup SFTP 7 days after status AIP_FINISHED
* cleanup working dir 7 days after status AIP_FINISHED

### version 0.9 ###
* flexible webservice location

### version 0.8 ###
* update file dc

### version 0.7 ###
* definition and readout of relevant file dc mets elemtns

### version 0.6 ###
* handle RIPID
* refactoring of update process

### version 0.5 ###
* definition and readout of dc mets elemtns
* update dc

### version 0.4 ###
* extract Rosetta mets
* implement file update

### version 0.3 ###
* find records to update
* copy records to update
* implement a comparator for all files

### version 0.2 ###
* implement insert of new SIP

### version 0.1 ###
* Database connectivity
* Iterate queue table with limited number of records
* simulate insert

### version 0.0.3 ###
* singleton implementation
* database base configuration

### version 0.0.2 ###
* implementation of global logger
* properties.config
* implemenation of configuration class

### version 0.0.1 ###
* setup bitbucket repo
* setup Eclipse project
