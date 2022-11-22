package models.launch.defects;


import lombok.*;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DefectsInfo {
    String defectName;
    String productBugQuantity;
}
