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
