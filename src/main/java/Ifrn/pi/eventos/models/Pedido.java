package Ifrn.pi.eventos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Doce doce;

	private int quantidade;
	private double valorTotal;
	private String formaDePagamento;

	public Pedido() {
	}

	public Pedido(Doce doce, int quantidade, String formaDePagamento) {
		this.doce = doce;
		this.quantidade = quantidade;
		this.valorTotal = doce.getPreco() * quantidade; // Calcula o valor total
		this.formaDePagamento = formaDePagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doce getDoce() {
		return doce;
	}

	public void setDoce(Doce doce) {
		this.doce = doce;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "Pedido{" + "id=" + id + ", doce=" + (doce != null ? doce.getNome() : "null") + ", quantidade="
				+ quantidade + ", valorTotal=" + valorTotal + ", formaDePagamento='" + formaDePagamento + '\'' + '}';
	}
}
