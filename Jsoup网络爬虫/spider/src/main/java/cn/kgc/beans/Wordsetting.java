package cn.kgc.beans;
import java.io.Serializable;

/***
*   
*/
public class Wordsetting implements Serializable {
        //主键
        private Integer id;
        //名称
        private String name;
        //停止词
        private String value;
        //radio;数据状态;0:未删除,1:已删除
        private Integer status;
        //get set 方法
        public void setId (Integer  id){
            this.id=id;
        }
        public  Integer getId(){
            return this.id;
        }
        public void setName (String  name){
            this.name=name;
        }
        public  String getName(){
            return this.name;
        }
        public void setValue (String  value){
            this.value=value;
        }
        public  String getValue(){
            return this.value;
        }
        public void setStatus (Integer  status){
            this.status=status;
        }
        public  Integer getStatus(){
            return this.status;
        }
}
