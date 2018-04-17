package com.pravin.onetoone;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "table_voter")
public class Voter {
	@Id
	@TableGenerator(
            name = "Voter_Gen",
            table = "Voter_Id_Generator",
            pkColumnName = "name",
            valueColumnName = "sequence",
            allocationSize = 1,
            initialValue = 101)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "Voter_Gen")
	private int voterId;
	public Voter() {
		// TODO Auto-generated constructor stub
	}
	public Voter(String name,String address){
		this.voterName=name;
		this.address=address;
	}
	@Column(name = "voter_name")
	private String voterName;

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	@Column(name = "voter_address")
	private String address;

	@OneToOne
	//@PrimaryKeyJoinColumn
	@JoinColumn(name="voterId")
	@Cascade(CascadeType.ALL)
	private Vote vote;
}
