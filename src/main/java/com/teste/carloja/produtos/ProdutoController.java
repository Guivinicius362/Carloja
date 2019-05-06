package com.teste.carloja.produtos;


import com.teste.carloja.error.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoModel> getProdutos() {

        return produtoService.listProdutos();
    }

    @PostMapping
    public ProdutoModel postProdutos(@RequestBody ProdutoModel produtoModel) {
        return produtoService.saveProduto(produtoModel);
    }

    @PostMapping("/{id}/comprar")
    public ProdutoModel postComprar( @PathVariable("id") Integer id) throws ApiException {
        return produtoService.comprarProduto(id);
    }

    @PutMapping("/{id}")
    public ProdutoModel putProdutos(@RequestBody ProdutoModel produtoModel, @PathVariable("id") Integer id) {
        produtoModel.setId(id);
        return produtoService.editProduto(produtoModel);
    }
}
