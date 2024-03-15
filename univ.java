import java.util.HashMap;
import java.util.Map;

// Classe base para Pessoa
class Pessoa {
    protected String nome;
    protected int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}

// Classe Aluno, derivada de Pessoa
class Aluno extends Pessoa {
    private String matricula;
    private Map<String, Disciplina> disciplinas;

    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);
        this.matricula = matricula;
        this.disciplinas = new HashMap<>();
    }

    public void matricular(Disciplina disciplina) {
        disciplinas.put(disciplina.getNome(), disciplina);
    }

    public void lancarNota(String nomeDisciplina, double nota) {
        Disciplina disciplina = disciplinas.get(nomeDisciplina);
        if (disciplina != null) {
            disciplina.adicionarNota(nota);
        } else {
            System.out.println("Aluno não matriculado na disciplina " + nomeDisciplina);
        }
    }

    public double calcularMedia(String nomeDisciplina) {
        Disciplina disciplina = disciplinas.get(nomeDisciplina);
        if (disciplina != null) {
            return disciplina.calcularMedia();
        } else {
            System.out.println("Aluno não matriculado na disciplina " + nomeDisciplina);
            return 0.0;
        }
    }
}

// Classe Disciplina
class Disciplina {
    private String nome;
    private Map<String, Double> notas;

    public Disciplina(String nome) {
        this.nome = nome;
        this.notas = new HashMap<>();
    }

    public void adicionarNota(double nota) {
        notas.put("Nota" + (notas.size() + 1), nota);
    }

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Double nota : notas.values()) {
            soma += nota;
        }
        return soma / notas.size();
    }

    public String getNome() {
        return nome;
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        // Criando alunos
        Aluno aluno1 = new Aluno("João", 20, "2021001");
        Aluno aluno2 = new Aluno("Maria", 22, "2021002");

        // Criando disciplinas
        Disciplina matematica = new Disciplina("Matemática");
        Disciplina fisica = new Disciplina("Física");

        // Matriculando alunos nas disciplinas
        aluno1.matricular(matematica);
        aluno2.matricular(matematica);
        aluno2.matricular(fisica);

        // Lançando notas
        aluno1.lancarNota("Matemática", 8.5);
        aluno1.lancarNota("Matemática", 7.0);
        aluno2.lancarNota("Matemática", 9.0);
        aluno2.lancarNota("Física", 6.5);

        // Calculando médias
        System.out.println("Média do aluno1 em Matemática: " + aluno1.calcularMedia("Matemática"));
        System.out.println("Média do aluno2 em Matemática: " + aluno2.calcularMedia("Matemática"));
        System.out.println("Média do aluno2 em Física: " + aluno2.calcularMedia("Física"));
    }
}