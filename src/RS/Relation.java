package RS;

public class Relation {
	
	Noeud initPoint;
	Noeud endPoint;
	String relation;
	
	public Relation(String ip,String ep,String r)
	{
		initPoint=new Noeud(ip);
		endPoint=new Noeud(ep);
		relation=r;
	}
     
	public void asString()
	{
		System.out.println(initPoint.nom+" "+relation+" "+endPoint.nom);
	}
}
