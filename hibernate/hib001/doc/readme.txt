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


get()方法和load()方法的区别
延迟加载（lazy load懒加载）是当在真正需要数据时，才执行SQL语句进行查询，避免了无谓的性能开销。
延迟加载策略的设置分为
[情况1].类级别的查询策略(只有true和false)
[情况2].一对多和多对多关联的查询策略
[情况3].多对一关联的查询策略。

[情况1].类(class)级别，只有true和false

[情况2].一对多和多对多关联的查询策略
one-to-many,set

lazy属性值	加载策略
true		延迟加载
false		立即加载
extra		增加延迟加载

[情况3].多对一关联的查询策略
many-to-one
lazy属性值	加载策略
proxy(默认)		延迟加载
no-proxy	无代理延迟加载
false		立即加载

延迟加载(lazy)是可以设置开启和关闭的。


load是根据类级的延迟加载级别

lazy值有true,false,extra(增强版延迟)。
看一下有多少人(size) ，或者看看有没有这个值(contains),就是对集合的一些操作。如果是true，不好，会查出所有值。
那extra值，就不会全部都查出来。

建议给set属性的lazy属性设置成extra



如果想查询雇员，雇员的信息设置成proxy。那么部门对象就是延迟加载的。
proxy 可以最大限度的推迟加载对象。
如果采取no-proxy.当访问getEmp，获取部门的方法，就必须加载部门了。不会等待。
默认情况下采取proxy

open session in View模式
在用户的每一次请求过程始终保持一个Session对象打开着。

