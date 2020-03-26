package simplon.co.linkinreal.dto;


public class ParticipantDto {


    private Long id;


    private String nickName;


    private Long eventId;


    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", eventId=" + eventId +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}



