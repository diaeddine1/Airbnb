package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Comment;
import btking.airbnb.Services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServices commentServices;

    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment comment){
        Comment added_comment = commentServices.save(comment);
        if(added_comment == null){
            return null;
        }
        return added_comment;
    }

    @PutMapping("update/{id}")
    public Comment updateComment(@RequestBody Comment comment,@PathVariable(required = true) String id){
        Comment updated_comment = commentServices.findById(id);
        if(updated_comment == null){
            return null;
        }

        return updated_comment = commentServices.update(comment);
    }

    @DeleteMapping("delete/{id}")
    public void deleteComment(@PathVariable(required = true) String id){
        Comment deleted_comment = commentServices.findById(id);
        if(deleted_comment == null){
            System.out.println("Cannot find comment with id to delete it : "+id);
        }
        commentServices.delete(deleted_comment);
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable String id){

        return commentServices.findById(id);

    }

    @GetMapping("/all")
    public List<Comment> getAllComments(){
        List<Comment> comments = commentServices.findAll();
        if(comments == null){
            return null;
        }
        return comments;
    }
}
