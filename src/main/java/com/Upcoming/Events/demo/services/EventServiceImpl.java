package com.Upcoming.Events.demo.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.repositories.EventRepository;

@Service
public class EventServiceImpl implements BaseService<Event>{

    @Autowired
    private EventRepository eventRepository;
    
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional 
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Event save(Event event) {

    //   if (event.getStyle() == "rock")  {
    //     event.setStyle("/rock.png");
    //   }
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Optional<Event> findById(Long id) {
       return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       eventRepository.deleteById(id);
    }
    
}
