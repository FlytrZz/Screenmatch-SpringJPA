package br.com.alura.screenmatch.model;

public enum Categoria {
	AÇAO("Action","Ação"),
	AVENTURA("Adventure","Aventura"),
	ANIMAÇÃO("Animation","Animação"),
	ROMANCE("Romance","Romance"),
	COMÉDIA("Comedy","Comédia"),
	DRAMA("Drama","Drama"),
	CRIME("Crime","Crime");
	
	private String categoriaOMDB;
	private String categoriaPortugues;
	
	Categoria(String categoriaOMDB, String categoriaPortugues){
		this.categoriaOMDB = categoriaOMDB;
		this.categoriaPortugues = categoriaPortugues;
	}
	
	public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOMDB.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
	
	public static Categoria fromPortugues(String texto) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(texto)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + texto);
    }
}
