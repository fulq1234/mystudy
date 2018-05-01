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