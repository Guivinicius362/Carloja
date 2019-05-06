package com.teste.carloja.produtos;

import com.teste.carloja.error.ApiException;
import com.teste.carloja.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {


    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel saveProduto(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public ProdutoModel comprarProduto(Integer id) throws ApiException {
        Optional<ProdutoModel> produtoDb = produtoRepository.findById(id);
        ProdutoModel produto = produtoDb.orElseThrow(() -> new ApiException("Produto não existe", 404));
        produto.comprar();
        String now = DateTimeUtils.getNow();

        return produtoRepository.save(produto);

    }

    public ProdutoModel editProduto(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    public List<ProdutoModel> listProdutos() {
        return produtoRepository.findAll();
    }
}
