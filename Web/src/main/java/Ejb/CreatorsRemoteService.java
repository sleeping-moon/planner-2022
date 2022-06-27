package Ejb;

import ejd.CreatorsRemoteServiceInterface;

import javax.ejb.Stateful;

@Stateful
public class CreatorsRemoteService implements CreatorsRemoteServiceInterface {
    @Override
    public void createdBy() {
        System.out.println("someone tries to read creators of this application");
    }
}
