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