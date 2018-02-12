/*GENERATE FIRST*/

//generate and convert value to string
def genref =  String.valueOf(((int)((Math.random()*919359467)+1))).substring(0, 8)
def uid = String.valueOf(((int)((Math.random()*99367467)+1))).substring(0, 7)
def scheduleID = uid
uid = "MOT_" + uid + "_MGP_AUT_M2_HD5"
def seriesID = String.valueOf(((int)((Math.random()*919359467)+1))).substring(0, 4)
def stackID = "STK" + String.valueOf(((int)((Math.random()*919359467)+1))).substring(0, 5)
def seasonID = String.valueOf(((int)((Math.random()*919359467)+1))).substring(0, 5)

/*GENERATE FIRST*/

//read file:
def xmlContents = new File("ss/template.xml").getText()

//where to put new file
def networkPath = "//172.16.103.220/MediaManager/SS_XML/ss.xml"

//put values in array
def vals = [genref,
scheduleID,
uid,
seriesID,
seasonID,
stackID]

def keys = ["{#Project#SSgenRef}",
"{#Project#SSscheduleid}",
"{#Project#SSuid}", 
"{#Project#SSseriesId}", 
"{#Project#SSseasonID}", 
"{#Project#SSStackID}"]

def key = keys[0]

for(def i = 0; i < 6; ++i){
		
	xmlContents = xmlContents.replace(keys[i],vals[i]);	
	
}

File file = new File(networkPath)

file.text = xmlContents

println ""
println "Done updating xml: genref:" + genref + " udi:" + uid + " scheduleID:" + scheduleID + " stackID:" + stackID
println ""

println "=================================================================="
println ""
/*DROP IMAGE==============================================================*/
 	

/*DROP ASSET==============================================================*/
//specify source & dest

def soureFileAss = "//172.16.103.220/Encoder_Area/Ardome/Automation files/automation_ssm.mxf"

def destFileAss = "//172.16.103.220/Encoder_Area/Ardome/AUTOMATION_SYSTEM/"

//get the file extension
def extAss =  soureFileAss.substring(soureFileAss.length() - 4)

//get new file name values
def newfilenameAss = destFileAss + uid + extAss

def srcStreamAss = new File(soureFileAss).newDataInputStream()
def dstStreamAss = new File(newfilenameAss).newDataOutputStream()

dstStreamAss << srcStreamAss
srcStreamAss.close()
dstStreamAss.close()

println "Done dropping asset: " + newfilenameAss
println ""
println "=================================================================="
println ""

logFolders("SS"+scheduleID)

def logFolders(scheduleID){ 
  
  File file = new File("../folders_SS.txt")
  
  file.text = ''
 
  file << scheduleID + "_" + "21103\n" +  scheduleID + "_" + "21203\n" + scheduleID + "_" + "21303\n" + scheduleID + "_" + "21403\n"
  
}

//check if dir exists
if(checkDir("../automation/collections/")){

  def jsonContents = new File("collections/Collection_MVP.postman_collection.json").getText()
  def jsonFilePath = "../automation/collections/Collection_MVP.postman_collection_1.json"

  jsonContents = jsonContents.replace("{gen_shid}",genref + "_SS" + scheduleID);

  File jsonFile = new File(jsonFilePath)

  jsonFile.text = jsonContents

} else {

throw new IOException("Folder path not found: " + "../automation/collections/")

}

def checkDir(def path){

def folder = new File( path )

  if( !folder.exists() ) {
    // Create all folders up-to and including B
    folder.mkdirs()
  } else {
    return true
  }

  return true
}


