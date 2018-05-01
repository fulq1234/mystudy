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
