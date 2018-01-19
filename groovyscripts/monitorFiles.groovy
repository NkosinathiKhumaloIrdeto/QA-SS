//check if file exists

import groovy.io.FileType

public class NoProcessedException extends Exception{
  
  public NoProcessedException(String message){
    super(message)
  }
  
}

Map<String, Integer> aMap = new HashMap<String, Integer>();

def newline = System.getProperty("line.separator")
def logPaths = new File("../folders_SS.txt")
def checkText = new File("../folders_SS.txt").getText()

//don't proceed if file is empty
if (!(checkText.length() > 0)) {
  
  println "Directory file not populated yet... aborting"
  
 // hudson.model.Result.SUCCESS;
  return
}

def a1 = 0;
def a2 = 0;
def a3 = 0;
def a4 = 0;

println "" 
println "Folder(s) scanned: " 

logPaths.withReader { reader ->
  while ((line = reader.readLine())!=null) {
           
 	foldername = "//172.16.103.220/MediaManager/irdeto.com/ContentGroup/" + line
    
	def list = []

	def dir = new File(foldername)
    
    println " " + foldername 
    
    if (dir.exists()){
    
		dir.eachFileRecurse (FileType.FILES) { file ->
       	
          println "   " +file.path
          if (file.path.indexOf("21103") > 0){a1++}  //explora
          if (file.path.indexOf("21203") > 0){a2++}  //explora
          if (file.path.indexOf("21303") > 0){a3++}  //explora
          if (file.path.indexOf("21403") > 0){a4++}  //nano
          
  			list << file
		}
      
      
    }
     
         }
      }

def fileExists(def path){
  
 return new File(path).exists()
  
}

/*
if(a1 > 1){println "SD - YES"} else {println "SD - X"}
if(a2 > 2){println "HD - YES"} else {println "HD - X"}
if(a3 > 2){println "EXP - YES"} else {println "EXP - X"}
if(a4 > 2){println "NAN - YES"} else {println "NAN - X"}
println ""
if((a1 >= 2) && (a2 >= 2) && (a3 >= 2) && (a4 >= 2)) {
  
  logPaths.text = ''
  
 // hudson.model.Result.SUCCESS;
} else {

  throw new NoProcessedException("Not all files were processed...");
  //hudson.model.Result.FAILURE;
}

*/


println ""
if(a1 > 0){println "SD - YES"} else {println "SD - X"}
if(a2 > 0){println "HD - YES"} else {println "HD - X"}
if(a3 > 0){println "EXP - YES"} else {println "EXP - X"}
if(a4 > 0){println "NAN - YES"} else {println "NAN - X"}
println ""

if((a1 >= 1) && (a2 >= 2) && (a3 >= 2) && (a4 >= 2)) {
  
  logPaths.text = ''
  
} else {

  throw new NoProcessedException("Not all files were processed...");

}
