package com.knoldus

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future



/**
  * Created by knoldus on 2/2/17.
  */
case class CustomException(s:String) extends Exception
class FileCount{

  val result = new ListBuffer[File]
  def getListOfFiles(path: String): List[File] = {

      val d = new File(path)

    if (d.isDirectory) {
      for (ls <- d.listFiles.toList) {
        if (ls.isFile) {
          result += ls
        }
        else
          getListOfFiles(ls.toString)
      }
    }
    if(!d.exists()){
      throw new CustomException("Directory not Exists")
    }
    result.toList
  }



}
object FileCount1 extends App{
val obj=new FileCount
  val res:Future[List[File]] = Future {
    obj.getListOfFiles("/home/knoldus/Assignment50Jan")
  }
  res onSuccess {
    case list=>for(ls: File <-list) println(ls)
  }
  res onFailure{
    case t=>println(t.getMessage)
  }
  Thread.sleep(10000)
}
