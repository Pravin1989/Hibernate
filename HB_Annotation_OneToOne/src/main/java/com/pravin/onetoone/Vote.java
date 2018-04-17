package com.pravin.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "table_vote")
public class Vote {

	@Id
	@Column(name = "vote_id")
	@TableGenerator(name = "Vote_Gen", table = "Vote_Id_Generator", pkColumnName = "name", valueColumnName = "sequence", allocationSize = 1, initialValue = 1001)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Vote_Gen")
	private int id;

	@Column(name = "voted_name")
	private String votedName;

	@OneToOne
	private Voter voter;

	public Vote() {
		// TODO Auto-generated constructor stub
	}

	public Vote(String votedName) {
		this.votedName = votedName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVotedName() {
		return votedName;
	}

	public void setVotedName(String votedName) {
		this.votedName = votedName;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}
}
