package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Message;
import btking.airbnb.Services.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageServices messageServices;

    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message){
        Message added_message = messageServices.save(message);
        if(added_message == null){
            return null;
        }
        return added_message;
    }

    @PutMapping("update/{id}")
    public Message updateMessage(@RequestBody Message message,@PathVariable(required = true) String id){
        Message updated_message = messageServices.findById(id);
        if(updated_message == null){
            return null;
        }

        return updated_message = messageServices.update(message);
    }

    @DeleteMapping("delete/{id}")
    public void deleteMessage(@PathVariable(required = true) String id){
        Message deleted_message = messageServices.findById(id);
        if(deleted_message == null){
            System.out.println("Cannot find message with id to delete it : "+id);
        }
        messageServices.delete(deleted_message);
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable String id){
        Message message = messageServices.findById(id);
        if(message == null){
            return null;
        }
        return  message;
    }

    @GetMapping("/all")
    public List<Message> getAllMessages(){
        List<Message> messages = messageServices.findAll();
        if(messages == null){
            return null;
        }
        return messages;
    }
}
