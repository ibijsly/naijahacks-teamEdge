package com.securedEdgePay.seeder;

import com.securedEdgePay.model.Status;
import com.securedEdgePay.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusSeeder {

    @Autowired
    StatusRepository statusRepository;

    public void seed(){

        System.out.println("Entered Status Seeder");

        if (statusRepository.count() > 2)
            return;

        statusRepository.save(new Status("Sent"));  /*Sent Awaiting Collection*/
        statusRepository.save(new Status("Received")); /*Received by the recipient already*/
        statusRepository.save(new Status("Pending"));   /*Received from bulk but awaiting payment from the sending party*/

    }
}
