package pageI;

import java.sql.Timestamp;

public class PageItem implements Comparable<PageItem>{
	private long pageId=0;
	private int ownerId=0;
	private int readLevel=-1;
	private String Title=null;
	private Timestamp editTime=null;
	public Timestamp getEditTime() {
		return editTime;
	}
	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}
	public long getPageId() {
		return pageId;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getReadLevel() {
		return readLevel;
	}
	public void setReadLevel(int readLevel) {
		this.readLevel = readLevel;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	@Override
	public int compareTo(PageItem o) {
		// TODO 自动生成的方法存根
		int n=0;
		if(this.getPageId()>o.getPageId()){
			n=1;
		}else{
			n=-1;
		}
		return n;
	}


}
