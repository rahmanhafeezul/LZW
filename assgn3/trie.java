class trie
{
	class node
	{
		int start,end;
		node next,prev;
		list positions;
		list children;
		void recurse(String pattern,int index,int nodeindex,int length)
		{
			if(pattern.charAt(index)=='*')
			{
				if(nodeindex>end)
				{
					if(children!=null)
					{
						node x = children.header.next;
						while(x!=children.trailer)
						{
							x.recurse(pattern,index,x.start,length);
							x=x.next;
						}
					}
				}
				else
					recurse(pattern,index,nodeindex+1,length+1);
			}
			if(nodeindex<=end && nodeindex<len)
			{
				if((pattern.charAt(index)=='*' || pattern.charAt(index)=='?') || pattern.charAt(index)==str.charAt(nodeindex))
				{
					index++;
					nodeindex++;
					if(index==pattern.length())
					{
						node t = positions.header.next;
						while(t!=positions.trailer)
						{
							addsolution(t.start,t.start+length);
							t=t.next;
						}
					}
					else if(nodeindex>end)
					{
						if(children!=null)
						{
							node x = children.header.next;
							while(x!=children.trailer)
							{
								x.recurse(pattern,index,x.start,length+1);
								x=x.next;
							}
						}
					}
					else
						recurse(pattern,index,nodeindex,length+1);
				}
			}
		}
	}
	class list
	{
		node header,trailer;
		list()
		{
			header=new node();
			trailer=new node();
			header.next=trailer;
			trailer.prev=header;
		}
		void add(int b)
		{
			node t = new node();
			t.start=b;
			t.prev=trailer.prev;
			t.next=trailer;
			t.next.prev=t;
			t.prev.next=t;
		}
	}
	node root;
	String str;
	int len;
	list solutions;
	trie(String input)
	{
		str=new String();
		str=input;
		root=new node();
		len=str.length();
		root.children=new list();
		for(int i=0;i<len;i++)
		{
			insert(i,len-1);
		}
	}
	void insert(int beg,int fin)
	{
		int beginning=beg;
		list ref=root.children;
		while(beg<=fin)
		{
			node t=ref.header.next;
			while(t!=ref.trailer)
			{
				if(str.charAt(beg)<=str.charAt(t.start))
					break;
				t=t.next;
			}
			if(t!=ref.trailer)
			{
				if(str.charAt(beg)==str.charAt(t.start))
				{
					int a = beg;
					int b = t.start;
					while(str.charAt(a)==str.charAt(b))
					{
						a++;
						b++;
						if(b==t.end+1 || a==fin+1)
							break;
					}
					if(a==fin+1)
					{
						if(b!=t.end+1)
						{
							node temp = new node();
							temp.children=t.children;
							t.children=new list();
							temp.positions=new list();
							node a1;
							a1=t.positions.header.next;
							while(a1!=t.positions.trailer)
							{
								temp.positions.add(a1.start);
								a1=a1.next;
							}
							t.children.header.next=temp;
							temp.prev=t.children.header;
							temp.next=t.children.trailer;
							t.children.trailer.prev=temp;
							
							temp.start=b;
							temp.end=t.end;
							t.end=b-1;
						}
						t.positions.add(beginning);
					}
					else if(b==(t.end+1))
					{
						t.positions.add(beginning);
						if(t.children==null)
							t.children=new list();
						ref=t.children;
					}
					else
					{
						node temp1 = new node();
						temp1.positions=new list();
						node a1;
						a1=t.positions.header.next;
						while(a1!=t.positions.trailer)
						{
							temp1.positions.add(a1.start);
							a1=a1.next;
						}
						t.positions.add(beginning);
						temp1.start=b;
						temp1.end=t.end;
						temp1.children=t.children;
						t.children=new list();
						t.children.header.next=temp1;
						temp1.prev=t.children.header;
						temp1.next=t.children.trailer;
						t.children.trailer.prev=temp1;
						t.end=b-1;
						ref=t.children;
					}
					beg=a;
				}
				else
				{
					node temp=new node();
					temp.start=beg;
					temp.end=fin;
					temp.positions=new list();
					temp.positions.add(beginning);
					temp.prev=t.prev;
					temp.prev.next=temp;
					temp.next=t;
					t.prev=temp;
					beg=fin+1;
				}
			 }
			 else
			 {
				node temp=new node();
				temp.start=beg;
				temp.end=fin;
				temp.positions=new list();
				temp.positions.add(beginning);
				temp.prev=t.prev;
				temp.prev.next=temp;
				temp.next=t;
				t.prev=temp;
				beg=fin+1;
			}
		}
	}
	void lookup(String pattern)
	{
		node x=root.children.header.next;
		solutions=null;
		solutions=new list();
		while(x!=root.children.trailer)
		{
			x.recurse(pattern,0,x.start,0);
			x=x.next;
		}
		x=solutions.header.next;
		if(solutions.header.next==solutions.trailer)
			System.out.println("No matches found");
		else
			System.out.println("The required pattern can be found :-");
		while(x!=solutions.trailer)
		{
			System.out.println("From "+x.start+" to "+x.end);
			x=x.next;
		}
	}
	void addsolution(int begindex,int endindex)
	{
		node t = solutions.header.next;
		while(t!=solutions.trailer)
		{
			if(begindex==t.start)
			{
				if(endindex<t.end)
					break;
			}
			else if(begindex<t.start)
				break;
			t=t.next;
		}
		node temp = new node();
		temp.start=begindex;
		temp.end=endindex;
		temp.next=t;
		temp.prev=t.prev;
		temp.next.prev=temp;
		temp.prev.next=temp;
	}
}
