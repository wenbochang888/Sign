package com.po;

/**
 * @Auther: Chang
 * @Date: 2018/10/3
 */
public class Record {

	String username;
	String title;
	String date;
	String info;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Record{" +
				"username='" + username + '\'' +
				", title='" + title + '\'' +
				", date='" + date + '\'' +
				", info='" + info + '\'' +
				'}';
	}
}
