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


get()������load()����������
�ӳټ��أ�lazy load�����أ��ǵ���������Ҫ����ʱ����ִ��SQL�����в�ѯ����������ν�����ܿ�����
�ӳټ��ز��Ե����÷�Ϊ
[���1].�༶��Ĳ�ѯ����(ֻ��true��false)
[���2].һ�Զ�Ͷ�Զ�����Ĳ�ѯ����
[���3].���һ�����Ĳ�ѯ���ԡ�

[���1].��(class)����ֻ��true��false

[���2].һ�Զ�Ͷ�Զ�����Ĳ�ѯ����
one-to-many,set

lazy����ֵ	���ز���
true		�ӳټ���
false		��������
extra		�����ӳټ���

[���3].���һ�����Ĳ�ѯ����
many-to-one
lazy����ֵ	���ز���
proxy(Ĭ��)		�ӳټ���
no-proxy	�޴����ӳټ���
false		��������

�ӳټ���(lazy)�ǿ������ÿ����͹رյġ�


load�Ǹ����༶���ӳټ��ؼ���

lazyֵ��true,false,extra(��ǿ���ӳ�)��
��һ���ж�����(size) �����߿�����û�����ֵ(contains),���ǶԼ��ϵ�һЩ�����������true�����ã���������ֵ��
��extraֵ���Ͳ���ȫ�����������

�����set���Ե�lazy�������ó�extra



������ѯ��Ա����Ա����Ϣ���ó�proxy����ô���Ŷ�������ӳټ��صġ�
proxy ��������޶ȵ��Ƴټ��ض���
�����ȡno-proxy.������getEmp����ȡ���ŵķ������ͱ�����ز����ˡ�����ȴ���
Ĭ������²�ȡproxy

open session in Viewģʽ
���û���ÿһ���������ʼ�ձ���һ��Session������š�

