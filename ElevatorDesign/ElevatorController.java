package ElevatorDesign;

import java.util.ArrayList;
import java.util.List;
public class ElevatorController {
    private final List<ElevatorCar> elevators;

    public ElevatorController(int numElevators, int capacity) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numElevators; i++) {
            ElevatorCar elevator = new ElevatorCar(i + 1, capacity);
            elevators.add(elevator);
            new Thread(elevator::run).start();
        }
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
        ElevatorCar optimalElevator = findOptimalElevator(sourceFloor, destinationFloor);
        optimalElevator.addRequest(new Request(sourceFloor, destinationFloor));
    }

    private ElevatorCar findOptimalElevator(int sourceFloor, int destinationFloor) {
        ElevatorCar optimalElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            int distance = Math.abs(sourceFloor - elevator.getCurrentFloor());
            if (distance < minDistance) {
                minDistance = distance;
                optimalElevator = elevator;
            }
        }

        return optimalElevator;
    }
}
