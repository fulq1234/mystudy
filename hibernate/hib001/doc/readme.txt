配置Dept到Emp的一对多关联
Dept持久化类
	public class Dept implements Serializable{
		private Set<Emp> emps = new HashSet<Emp>();
		//省略其他属性及getter,setter访问器
	}
Dept.hbm.xml
	<class name="cn.hibernatedemo.entity.Dept" table="DEPT">
		... ...
		<set name="emps">
			<key column = "DEPTNO"></key>
			<one-to-many class="cn.hibernatedemo.entity.Emp"/>
		</set>
	</class>

在一的一方，写个Set集合，需要初始化;


cascade属性
级联关系下的增删改查功能

一对多的情况下， cascade放在一的一方。

inverse
在级联保存两个雇员以后，还要通过一个额外的update语句，更新下外键值。
如果确保正确，就不需要这个update语句。怎么办？

inverse属性指定了关联关系中的方法
1.inverse设置为false,则为主动方,由主动方负责维护关联关系，默认是false
2.inverse设置为true,不负责维护关联关系

维护外键（fk）的权利,如果是情况1，就执行update 更新外键语句，如果是情况2,就不执行update语句。

1.在建立两个对象的双向关联时，应该同时修改两个关联对象的相关属性
2.建立inverse设置为true

多对多映射

项目表(PROGECT),雇员表(EMPLOTEE)
一个项目可以有多个雇员。
一个雇员也可以参加多个项目。项目和雇员就是多对多关系
他们不能直接产生关系，需要一个关系表（ PROEMP）

配置Project和Employee的多对多双向关联
public class Project implements java.io.Serializable{
	private Integer proid;
	private String proname;
	private Set<Employee> employees = new HashSet<Employee>(0);
}

public class Employee implements java.io.Serializable{
	private Integer empid;
	private String empname;
	private Set<Project> projects = new HashSet<Project>(0);
}

<class name="cn.hibernatedemo.entity.Project" table="PROJECT">
	<id name="proid" type="java.lang.Integer">
		<column name="PROID" precision="6" scale="0"/>
		<generator class="assigned"/>
	</id>
	... ...
	<set name="employees" table="PROEMP" cascade="save-update">
	<!--table属性是关系表-->
		<key column="RPROID"/><!--关系表的外键字段-->
		<many-to-many class="cn.hibernatedemo.entity.Employee" column="REMPID"/><!--column雇员的外键-->
	</set>
</class>

对应另外一方
<set name="projects" table="PROEMP" inverse="true">
	<key column="REMPID"/>
	<many-to-many class="cn.hibernatedemo.entity.Project" column="RPROID"/>
</set>

两方都有外键，所以有一个是主控方，一方是被控方。


