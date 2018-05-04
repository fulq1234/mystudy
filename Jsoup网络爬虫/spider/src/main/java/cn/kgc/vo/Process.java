package cn.kgc.vo;

import cn.kgc.beans.CrawlerTask;

import java.io.Serializable;

/***
 * 进度统计实体
 */
public class Process implements Serializable{
    //任务Id
    private Long  taskId;
    //进度名称
    private String taskName;
    //当前执行位置
    private Integer index;
    //执行总数
    private Integer total;

    private Integer percent;

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Process(CrawlerTask crawlerTask){
        this.index=0;
        this.percent=0;
        this.taskId=crawlerTask.getId();
        this.taskName=crawlerTask.getTaskName();
        this.total=crawlerTask.getEndPage();
    }

    
}
