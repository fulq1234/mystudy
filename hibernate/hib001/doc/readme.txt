����Dept��Emp��һ�Զ����
Dept�־û���
	public class Dept implements Serializable{
		private Set<Emp> emps = new HashSet<Emp>();
		//ʡ���������Լ�getter,setter������
	}
Dept.hbm.xml
	<class name="cn.hibernatedemo.entity.Dept" table="DEPT">
		... ...
		<set name="emps">
			<key column = "DEPTNO"></key>
			<one-to-many class="cn.hibernatedemo.entity.Emp"/>
		</set>
	</class>

��һ��һ����д��Set���ϣ���Ҫ��ʼ��;


cascade����
������ϵ�µ���ɾ�Ĳ鹦��

һ�Զ������£� cascade����һ��һ����

inverse
�ڼ�������������Ա�Ժ󣬻�Ҫͨ��һ�������update��䣬���������ֵ��
���ȷ����ȷ���Ͳ���Ҫ���update��䡣��ô�죿

inverse����ָ���˹�����ϵ�еķ���
1.inverse����Ϊfalse,��Ϊ������,������������ά��������ϵ��Ĭ����false
2.inverse����Ϊtrue,������ά��������ϵ

ά�������fk����Ȩ��,��������1����ִ��update ���������䣬��������2,�Ͳ�ִ��update��䡣

1.�ڽ������������˫�����ʱ��Ӧ��ͬʱ�޸���������������������
2.����inverse����Ϊtrue

��Զ�ӳ��

��Ŀ��(PROGECT),��Ա��(EMPLOTEE)
һ����Ŀ�����ж����Ա��
һ����ԱҲ���ԲμӶ����Ŀ����Ŀ�͹�Ա���Ƕ�Զ��ϵ
���ǲ���ֱ�Ӳ�����ϵ����Ҫһ����ϵ�� PROEMP��

����Project��Employee�Ķ�Զ�˫�����
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
	<!--table�����ǹ�ϵ��-->
		<key column="RPROID"/><!--��ϵ�������ֶ�-->
		<many-to-many class="cn.hibernatedemo.entity.Employee" column="REMPID"/><!--column��Ա�����-->
	</set>
</class>

��Ӧ����һ��
<set name="projects" table="PROEMP" inverse="true">
	<key column="REMPID"/>
	<many-to-many class="cn.hibernatedemo.entity.Project" column="RPROID"/>
</set>

�������������������һ�������ط���һ���Ǳ��ط���


