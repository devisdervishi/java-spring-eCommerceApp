package devis.springboot.app.controllers;

import devis.springboot.app.entity.Member;
import devis.springboot.app.entity.Product;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.services.MemberService;
import devis.springboot.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("app/admin")
public class AdminMemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;


    @RequestMapping("/dashboard")
    public String hello(){
        return "Hello admin";
    }
    @PostMapping("/member")
    public Member saveMember(@RequestBody Member member){
        return memberService.saveMember(member);
    }
    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable("id")Long id) throws EntityNotFoundException {
        return memberService.getMember(id);
    }
    @DeleteMapping("/member/{id}")
    public String deleteMember(@PathVariable("id") Long id) throws EntityNotFoundException {
        return memberService.deleteMember(id);
    }

    @PutMapping("/member/{id}")
    public Member updateMember(@RequestBody Member member,@PathVariable("id")Long member_id) throws EntityNotFoundException {
        return memberService.updateMemberData(member,member_id);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @GetMapping("product/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws EntityNotFoundException {
        return productService.getProduct(id);
    }
    @PutMapping("product/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable("id") Long product_id) throws EntityNotFoundException {
        return productService.updateProduct(product,product_id);
    }
    @DeleteMapping("product/{id}")
    public String deleteProduct(@PathVariable("id")Long id) throws EntityNotFoundException {
        return productService.deleteProduct(id);
    }


}
