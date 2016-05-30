package pattern.behavior.mediator;

public class ColleagueB extends AbstractColleague {
	@Override
	public void setNumber( int number, AbstractMediator am ){
		this.number = number;
		am.BaffectA();
	}
}
