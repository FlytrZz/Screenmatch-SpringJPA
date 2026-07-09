package br.com.alura.screenmatch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import com.fasterxml.jackson.annotation.JsonAlias;

import br.com.alura.screenmatch.service.tradução.ConsultaMyMemory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String titulo;
	
	private Integer totalTemporadas;
	
	private double avaliacao;
	
	@Column(columnDefinition = "TEXT")
	private String atores;
	
	@Enumerated(EnumType.STRING)
	private Categoria gênero;
	
	private String poster;
	
	@Column(columnDefinition = "TEXT")
	private String sinopse;
	
	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Episodio>episodios = new ArrayList<>();
	
	public Serie() {}
	
	public Serie(DadosSerie dadosSerie) {
		this.titulo = dadosSerie.titulo();
		this.totalTemporadas = dadosSerie.totalTemporadas();
		try{
			this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
		} catch(NumberFormatException e) {
			System.out.println(0);
		}
		this.gênero = Categoria.fromString(dadosSerie.gênero().split(",")[0].trim());
		this.atores = dadosSerie.atores();
		this.poster = dadosSerie.poster();
		this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse());
	}
	
	@Override
	public String toString() {
		return "gênero=" + gênero +", titulo=" + titulo + ", totalTemporadas=" + totalTemporadas + ", avaliacao=" + avaliacao
				+ ", atores=" + atores +", poster=" + poster + ", sinopse=" + sinopse+", episodios=" + episodios;
	}

	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public Categoria getGênero() {
		return gênero;
	}

	public void setGênero(Categoria gênero) {
		this.gênero = gênero;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		episodios.forEach(e -> e.setSerie(this));
		this.episodios = episodios;
	}
}
