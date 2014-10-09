package com.mega.pack;

import java.util.List;

public class Sale
{
	private String title;
	private String data;
	private List<Card> cards;
	public void setCards(List<Card> cards)
	{
		this.cards = cards;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public List<Card> getCards()
	{
		return cards;
	}
	
}
