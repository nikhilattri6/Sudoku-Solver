package com.example.sudokusolver;


import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData.Item;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) public class MainActivity extends Activity implements OnClickListener {

	
	EditText ed[]=new EditText[9];
	Button btn,btn1;
	RelativeLayout rl;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ed[0]=(EditText)findViewById(R.id.editText1);
        ed[1]=(EditText)findViewById(R.id.editText2);
        ed[2]=(EditText)findViewById(R.id.editText3);
        ed[3]=(EditText)findViewById(R.id.editText4);
        ed[4]=(EditText)findViewById(R.id.editText5);
        ed[5]=(EditText)findViewById(R.id.editText6);
        ed[6]=(EditText)findViewById(R.id.editText7);
        ed[7]=(EditText)findViewById(R.id.editText8);
        ed[8]=(EditText)findViewById(R.id.editText9);
        
        rl=(RelativeLayout)findViewById(R.id.rl1);
                
        btn=(Button)findViewById(R.id.button1);        
        btn.setOnClickListener(this);
        btn1=(Button)findViewById(R.id.button2);        
        btn1.setOnClickListener(this);
        
        Toast.makeText(this,"Use ZERO in place of blank",Toast.LENGTH_LONG).show();
        
    }

	
	
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub

	
	
	getMenuInflater().inflate(R.menu.main, menu);
	
	return super.onCreateOptionsMenu(menu);
}


@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub

	int x=item.getItemId();
	
	switch(x)
	{
	case R.id.item1:	rl.setBackgroundResource(R.drawable.bg1);
		break;
	case R.id.item2:	rl.setBackgroundResource(R.drawable.bg2);
		break;
	case R.id.item3:	rl.setBackgroundResource(R.drawable.bg3);
		break;
	}
	
	return super.onOptionsItemSelected(item);
}
	
	
	
	
	
	
	//***************************************************
	
	
	public int checkbox(int x,int y,int ele,int grid[][])
	{
		int i=3*(x/3);
		int j=3*(y/3);
		
		
		for(int k=i;k<i+3;k++)
		{
			for(int l=j;l<j+3;l++)
			{      
			    
				if(grid[k][l]==ele)
				{
					return 0;					// box not available
				}
			}
			
		}
		
		return 1;								// box available
	}




	public int checkC(int x,int ele,int grid[][])
	{
		for(int i=0;i<9;i++)
		{
			if(grid[i][x]==ele)
			return 0;							// box not available
		}
		
		return 1;								// box available
	}



	public int checkR(int y,int ele,int grid[][])
	{
		for(int i=0;i<9;i++)
		{
			if(grid[y][i]==ele)
			return 0;							// box not available
		}
		
		return 1;								// box available
	}




	public int call(int i,int j,int grid[][])
	{
		
		if(j>=9)
		{
			
			j=0;
			i++;
		}
		if(i>=9)
		{
			for(int g=0;g<9;g++)
			{
				String s=new String();
				s="";
				int zz=0;
				for(int h=8;h>=0;h--)
				{	
					zz++;
					if(zz==4)
					{
						s=s+"  ";
						zz=1;
					}
					
					s=s+grid[g][h];
				}
				
				
				
				for(int k=0;k<9;k++)
				{
				int maxLength = 13;
				InputFilter[] fArray = new InputFilter[1];
				fArray[0] = new InputFilter.LengthFilter(maxLength);
				ed[k].setFilters(fArray);
				}
							
				ed[g].setText(s);
				
			}
			
			return 1;
		}
		
		int temp=j+1;
						if(grid[i][j]==0)
						{
							
							for(int k=1;k<10;k++)
							{
								
								if((checkbox(i,j,k,grid)==1) && (checkC(j,k,grid)==1) && (checkR(i,k,grid)==1))
								{
									grid[i][j]=k;
								    int rr=call(i,temp,grid);
									if(rr==1)
										return rr;
								    grid[i][j]=0;
									
								}
							}
						}
						else
						{
							 int rr=call(i,temp,grid);
							 if(rr==1)
							 {
								 return rr;
							 }
						}
						
			return 0;
	}

	
	
	
	public void result()
	{
		int flag=1;
 		int grid[][]=new int[9][9];
 		
 	     /*
 		int grid[][]=new int[][] {	
 							{1,0,0,0,0,6,8,2,5},
 							{0,0,0,0,0,1,4,0,0},
 							{0,0,0,0,8,0,0,0,6},
 							{0,0,0,0,0,5,0,0,3},
 							{3,7,2,0,0,0,5,1,9},
 							{6,0,0,1,0,0,0,0,0},			
 							{2,0,0,0,9,0,0,0,0},
 							{0,0,3,6,0,0,0,0,0},
 							{8,5,9,3,0,0,0,0,2}			
 									};
 		*/
 		
 	
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		for(int i=0;i<9;i++)
 		{
 			
 			if(ed[i].getText().toString().isEmpty()) 	
 			{
 				Toast t1= Toast.makeText(this,"Row "+(i+1)+" is Empty",Toast.LENGTH_SHORT); 
				t1.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 120);
				t1.show();
				flag=0;
				break;
				
 			}
 			
 			if( ed[i].getText().toString().length()!=9) 	
 			{
 				Toast t1= Toast.makeText(this,"Row "+(i+1)+" :Input Missing",Toast.LENGTH_SHORT); 
				t1.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 120);
				t1.show();
				flag=0;
				break;
 			}
 			
 			int x=Integer.parseInt(ed[i].getText().toString());
 
 			for(int j=0;j<9;j++)
 			{
 				grid[i][j]=x%10;
 				x=x/10;
 			} 						
 		}
 		
 		
 		
 		if(flag==1)
 		{
 			Toast.makeText(this,"GRID COMPLETE",Toast.LENGTH_SHORT).show();
 			if(call(0,0,grid)==1)
			{	Toast.makeText(this,"SUDOKU SOLVED",Toast.LENGTH_SHORT).show();	}
 			else if(call(0,0,grid)==0)
 			{	Toast.makeText(this,"PUZZLE NOT POSSIBLE",Toast.LENGTH_SHORT).show();	}
 			
 		}
	}
	
	
	public void clr1()
	{
		for(int i=0;i<9;i++)
 		{
			ed[i].setText("");
			int maxLength = 9;
		InputFilter[] fArray = new InputFilter[1];
		fArray[0] = new InputFilter.LengthFilter(maxLength);
		ed[i].setFilters(fArray);
		}
		ed[0].requestFocus();
		Toast.makeText(this,"ALL CLEARED",Toast.LENGTH_SHORT).show();
	}
	
	
 	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
 		
 		
 		
 		switch(arg0.getId())
 		{
 		case R.id.button1 : result();
 							break;
 		case R.id.button2 : clr1();
 							break;
 		}		
		
	}
    
}
