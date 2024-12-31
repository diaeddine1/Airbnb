package btking.airbnb.Services;

import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Message;
import btking.airbnb.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServices implements Idao<Message> {

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        return save(message);
    }

    @Override
    public void delete(Message message) {
        messageRepository.delete(message);
    }

    @Override
    public Message findById(String id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
